using System;
using System.Collections.Generic;
namespace CList
{
	class MainClass
	{
		public static void Main(string[] args)
		{
			List<string> propertyNames = new List<string>(new string[] { "Id", "Nombre", "Precio", "Categoria" });

			//List<string> fieldsWithoutId = propertyNames.GetRange(1, propertyNames.Count - 1);

			//List<string> fieldsWithoutId = new List<string>(propertyNames);
			//fieldsWithoutId.Remove(0);

			//List<string> fieldsWithoutId = new List<string>();
			//foreach (int index = 1; index < propertyNames.Count; index++)
			//fieldsWithoutId.Add(propertyNames[index]);

			//List<string> fieldsWithoutId = new List<string>();
			//foreach (string item in propertyNames)
			//{
			//	if (item != "Id")
			//		fieldsWithoutId.Add("@" + item);
			//}


			//List<string> fieldsWithoutId = propertyNames.FindAll(item => item != "Id");
			//List<string> parameters = new List<string>();
			//fieldsWithoutId.ForEach(item => parameters.Add("@" + item));

			List<string> fieldsWithoutId = new List<string>();
			List<string> parameters = new List<string>();
			for (int index = 1; index < propertyNames.Count; index++)
			{
				fieldsWithoutId.Add(propertyNames[index]);
				parameters.Add("@" + propertyNames[index]);
			}



			string fieldNameCsv = string.Join(", ", fieldsWithoutId).ToLower();
			Console.WriteLine("FieldNamesCsv=" + fieldNameCsv);

			string parametersCsv = string.Join(", ", parameters).ToLower();
			Console.WriteLine("ParametersCsv=" + parametersCsv);


			//  update articulo set nombre=@nombre, precio=@precio, categoria=@categoria where id=@id
			//  update {0} set {1} where {2}=@id
			List<string> filedParametersPairs = new List<string>();
			for (int index = 1; index < propertyNames.Count; index++)
			{
				string item = propertyNames[index];
				filedParametersPairs.Add(item + "=@" + item);
			}
			string filedParametersPairsCsv = string.Join(",", filedParametersPairs).ToLower();
			Console.WriteLine("fieldParameterPairsCsv= " + filedParametersPairsCsv);





		}
	}


}