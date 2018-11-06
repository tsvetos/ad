using System;
namespace Serpis.Ad.Ventas
{
    public class Articulo
    {
        private decimal precio;
        private ulong? categoria;
        private ulong id;
        private string nombre;
  //      public CArticulo()
  //      {
  //      }

        //public CArticulo(ulong id, string nombre, decimal precio, int categoria){
        //  this.id = id;
        //  this.nombre = nombre;
        //  this.precio = precio;
        //  this.categoria = categoria;

        //}
        public ulong Id
        {
            get { return id; }
            set { id = value; }
        }

        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }
        public ulong? Categoria
          {
            get { return categoria; }
            set { categoria = value; }
        }

        public decimal Precio
        {
            get { return precio; }
            set { precio = value; }
        }
        

    }
}