package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
		// Representamos el grafo por listas de vecinos
		private ArrayList< HashSet<Integer> > _vecinos;
		
		// El n�mero de v�rtices queda fijado en el constructor
		public Grafo(int cantidadDeVertices)
		{
			_vecinos = new ArrayList< HashSet<Integer> >(cantidadDeVertices);
			
			for(int i=0; i<cantidadDeVertices; ++i)
				_vecinos.add(new HashSet<Integer>());
		}
		
		// Se agrega una arista en O(1)
		public void agregarArista(int i, int j)
		{
			verificarVertice(i, "agregar una arista");
			verificarVertice(j, "agregar una arista");
			verificarDistintos(i, j, "agregar una arista");
			
			_vecinos.get(i).add(j);
			_vecinos.get(j).add(i);
		}

		// Informa si existe una arista, en O(1)
		public boolean existeArista(int i, int j)
		{
			verificarVertice(i, "consultar una arista");
			verificarVertice(j, "consultar una arista");

			return _vecinos.get(i).contains(j);
		}

		// Eliminaci�n de una arista, en O(1)
		public void eliminarArista(int i, int j)
		{
			verificarVertice(i, "eliminar una arista");
			verificarVertice(j, "eliminar una arista");

			_vecinos.get(i).remove(j);
			_vecinos.get(j).remove(i);
		}
		
		// Grado de un v�rtice (cantidad de vecinos), en O(1)
		public int grado(int i)
		{
			return vecinos(i).size();
		}
		
		// Conjunto de vecinos de un v�rtice, en O(1)
		@SuppressWarnings("unchecked")
		public Set<Integer> vecinos(int i)
		{
			verificarVertice(i, "consultar los vecinos");
			return (Set<Integer>) _vecinos.get(i).clone();
		}
			
		// Lanza una excepci�n si el �ndice v est� fuera de rango para los v�rtices
		private void verificarVertice(int v, String accion)
		{
			if( v<0 || v>=vertices() )
				throw new IllegalArgumentException("Se intent� " + accion + " con un �ndice inexistente! vertice = " + v);
		}

		// Lanza una excepci�n si los �ndices son iguales
		private void verificarDistintos(int i, int j, String accion)
		{
			if( i == j )
				throw new IllegalArgumentException("Se intent� " + accion + " con los dos v�rtices iguales! vertice = " + i);
		}
		
		// Cantidad de v�rtices
		public int vertices()
		{
			return _vecinos.size();
		}
	}

