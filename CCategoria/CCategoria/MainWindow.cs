using System;
using MySql.Data.MySqlClient;
using System.Data;
using System.Collections.Generic;
using Gtk;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
		Build();



		IDbConnection dbConnection = new MySqlConnection(
                "server=localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
            );
        dbConnection.Open();

		treeView.AppendColumn("ID", new CellRendererText(), "text", 0);
		treeView.AppendColumn("Nombre", new CellRendererText(), "text", 1);

	//	ListStore listStore = new ListStore(typeof(ulong), typeof(string)); //ulong acepta el formato del id
		ListStore listStore = new ListStore(typeof(string), typeof(string)); //con string hay que usar ToString()
		treeView.Model = listStore;
        

		//listStore.AppendValues("1", "cat 1");
		//listStore.AppendValues("2", "cat 2");

		IDbCommand dbCommand = dbConnection.CreateCommand();
		dbCommand.CommandText = "select id, nombre from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"].ToString());


		dbConnection.Close();
    }
    
    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
