using System;
using MySql.Data.MySqlClient;
using System.Data;
using System.Collections.Generic;

namespace CMySql
{
	class MainClass
	{
		private static string[] getFieldNames(IDataReader dataReader ){
			int fieldCount = dataReader.FieldCount;
			//string[] fieldNames = new string[fieldCount];
			//for (int index = 0; index < fieldCount; index++)
			//fieldNames[index] = dataReader.GetName(index);
			//	return fieldNames;
			List<String> fieldNameList = new List<string>();
			for (int index = 0; index < fieldCount; index++)
				fieldNameList.Add(dataReader.GetName(index));
			//fieldNameList.Add("id");
			//fieldNameList.Add("nombre");
			return fieldNameList.ToArray();
				
			
		}
		public static void Main (string[] args)
		{
			IDbConnection dbConnection = new MySqlConnection (
				"server=localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);
			dbConnection.Open();

			IDbCommand dbCommand = dbConnection.CreateCommand();
			dbCommand.CommandText = "select id, nombre,'dato fijo' from categoria order by id";
			IDataReader dataReader = dbCommand.ExecuteReader();
			string[] fieldNames = getFieldNames(dataReader);
			foreach (string fieldName in fieldNames)
				Console.WriteLine("Columna=" + fieldName);


			//for (int index = 0; index < dataReader.FieldCount; index++)
			//	Console.WriteLine("Columna {0} = {1} ", index, dataReader.GetName(index));

			//while (dataReader.Read())
			//	Console.WriteLine("id = '{0}'|nombre = '{1}' ", dataReader["id"], dataReader["nombre"]);
			dbConnection.Close();


			dbConnection.Close();
		}
	}
}
