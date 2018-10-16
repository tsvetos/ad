﻿using System;
using MySql.Data.MySqlClient;
using Gtk;
using Serpis.Ad;

namespace CCategoria {
	
    class MainClass {
		
        public static void Main(string[] args) {

			App.Instance.DbConnection = new MySqlConnection(
              "server=localhost;database=dbprueba;user=root;password=sistemas;ssl-mode=none"
          );

            App.Instance.DbConnection.Open();
			
            Application.Init();
            MainWindow win = new MainWindow();
            win.Show();

            Application.Run();

			App.Instance.DbConnection.Close();
        }
    }
}