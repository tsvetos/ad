﻿using System;
using Gtk;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

		treeView.AppendColumn("ID", new CellRendererText(), "text", 0);
		treeView.AppendColumn("Nombre", new CellRendererText(), "text", 1);

		ListStore listStore = new ListStore(typeof(string), typeof(string));
		treeView.Model = listStore;
		listStore.AppendValues("1", "cat 1");
		listStore.AppendValues("2", "cat 2");
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
