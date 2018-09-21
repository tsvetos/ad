using System;
using MySql.Data.MySqlClient;
using System.Data;
using System.Collections.Generic;
using Gtk;
using CCategoria;

public partial class MainWindow : Gtk.Window
{
	void HandleCellLayoutDataFunc(CellLayout cell_layout, CellRenderer cell, TreeModel tree_model, TreeIter iter)
	{
	}


	public MainWindow() : base(Gtk.WindowType.Toplevel)
	{
		Build();



		IDbConnection dbConnection = new MySqlConnection(
				"server=localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);
		dbConnection.Open();

		//treeView.AppendColumn("ID", new CellRendererText(), "text", 0);
		//treeView.AppendColumn("Nombre", new CellRendererText(), "text", 1);
        
		CellRendererText cellRendererText = new CellRendererText();
		//treeView.AppendColumn(
		//	"ID", 
		//	cellRendererText,
		//	delegate (TreeViewColumn tree_column, CellRenderer cell, TreeModel tree_model, TreeIter iter){
		//		//Categoria categoria = (Categoria)tree_model.GetValue(iter, 0);
		//	    //cellRendererText.Text = categoria.Id + "";
		//	object model = tree_model.GetValue(iter, 0);
  //              object value = model.GetType().GetProperty("Id").GetValue(model);
  //              cellRendererText.Text = value + "";
		//   } 
		// );

		treeView.AppendColumn(
            "Nombre",
            cellRendererText,
            delegate (TreeViewColumn tree_column, CellRenderer cell, TreeModel tree_model, TreeIter iter) {
				//Categoria categoria = (Categoria)tree_model.GetValue(iter, 0);
				//cellRendererText.Text = categoria.Nombre + "";
				object model = tree_model.GetValue(iter, 0);
				object value = model.GetType().GetProperty("Nombre").GetValue(model);
				cellRendererText.Text = value + "";
            }
         );

		string[] properties = new string[] { "Id", "Nombre" };

		foreach (string property in properties){
			string propertyName = property;

			treeView.AppendColumn(
				propertyName,
                cellRendererText,
                delegate (TreeViewColumn tree_column, CellRenderer cell, TreeModel tree_model, TreeIter iter) {
                //Categoria categoria = (Categoria)tree_model.GetValue(iter, 0);
                //cellRendererText.Text = categoria.Id + "";
                object model = tree_model.GetValue(iter, 0);
                object value = model.GetType().GetProperty("Id").GetValue(model);
                cellRendererText.Text = value + "";
            }
         );

         

		}

		//	ListStore listStore = new ListStore(typeof(ulong), typeof(string)); //ulong acepta el formato del id
		//	ListStore listStore = new ListStore(typeof(string), typeof(string)); //con string hay que usar ToString()

		ListStore listStore = new ListStore(typeof(Categoria));
		treeView.Model = listStore;
        

		//listStore.AppendValues("1", "cat 1");
		//listStore.AppendValues("2", "cat 2");

		IDbCommand dbCommand = dbConnection.CreateCommand();
		dbCommand.CommandText = "select id, nombre from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(new Categoria((ulong)dataReader["id"], (string)dataReader["nombre"]));

		dataReader.Close();
		dbConnection.Close();
    }
    
    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
