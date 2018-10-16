using Gtk;

using System;
using System.Data;

using CCategoria;
using Serpis.Ad;


public partial class MainWindow : Gtk.Window {



    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();

		Title = "Categoria";
      

        TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);

        newAction.Activated += delegate {
            new CategoriaWindow(new Categoria());
        };

        editAction.Activated += delegate {
            object id = TreeViewHelper.GetId(treeView);
            Console.WriteLine("Id=" + id);
            Categoria categoria = CategoriaDao.Load(id);
            new CategoriaWindow(categoria);
        };

		refreshAction.Activated += delegate {
           
			TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);
        };

		deleteAction.Activated += delegate {
			object id = TreeViewHelper.GetId(treeView);
			if (WindowHelper.Confirm(this, "Quieres eliminar el registro?"))
				Console.WriteLine("Eliminamos la categoria con id= "+id);

             
				//CategoriaDao.Delete(id);

        };


        treeView.Selection.Changed += delegate {
            refreshUI();
        };

        refreshUI();
    }

   
    private void refreshUI() {
        bool treeViewIsSelected = treeView.Selection.CountSelectedRows() > 0;
        editAction.Sensitive = treeViewIsSelected;
        deleteAction.Sensitive = treeViewIsSelected;
    }

    private void insert() {
        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "insert into categoria (nombre) values ('categoria 4')";
        dbCommand.ExecuteNonQuery();
    }

    private void update() {
        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "update categoria set nombre='categoria 4 modificada' where id=4";
        dbCommand.ExecuteNonQuery();
    }

    private void update(Categoria categoria) {
        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "update categoria set nombre=@nombre where id=@id"; //formateo de parámetros
        DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
        DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
        dbCommand.ExecuteNonQuery();
    }

    private void delete() {
        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "delete from categoria where id=4";
        dbCommand.ExecuteNonQuery();
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        App.Instance.DbConnection.Close();
        Application.Quit();
        a.RetVal = true;
    }

   

}