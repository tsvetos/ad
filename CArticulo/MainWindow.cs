using System;
using Gtk;
using Serpis.Ad;
using Serpis.Ad.Ventas;

//public class EntityDaoArticulo : EntityDao<CArticulo> { }


public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();
        EntityDao<Articulo> articuloDao = new EntityDao<Articulo>();
        TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre", "Precio", "Categoria" }, articuloDao.Enumerable);
    }



    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}