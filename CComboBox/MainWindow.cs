using System;
using Gtk;

public partial class MainWindow : Gtk.Window
{
	public MainWindow() : base(Gtk.WindowType.Toplevel)
	{
		Build();

		//var cellRenderText = new CellRendererText();
		//comboBox.PackStart(cellRenderText, expand: false);
		//comboBox.AddAttribute(cellRenderText, "text", 0);



		var labelCellRenderText = new CellRendererText();
		comboBox.PackStart(labelCellRenderText, expand: false);
		comboBox.AddAttribute(labelCellRenderText, "text", 1);
         
		var list = new[]{
			new {Id = 1, Nombre = "cat1"},
			new {Id = 2, Nombre = "cat2"},
			new {Id = 3, Nombre = "cat3"}

		};

		int initialId = 1;


		ListStore listStore = new ListStore(typeof(object), typeof(string));
		TreeIter initialTreeIter = listStore.AppendValues(null, "<sin asignar>");
		foreach (var item in list)
		{
			TreeIter treeIter = listStore.AppendValues(item, item.Nombre);
			if (item.Id == initialId)
				initialTreeIter = treeIter;
		}

		//ListStore listStore = new ListStore(typeof(int), typeof(string));
		//listStore.AppendValues(1, "cat1");
		//listStore.AppendValues(2, "cat2");
		//listStore.AppendValues(3, "cat3");



		comboBox.Model = listStore;
		comboBox.SetActiveIter(initialTreeIter);
    }

    

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
