package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
		// Representamos el grafo por listas de vecinos
		private ArrayList< HashSet<Integer> > _vecinos;
		
		// El número de vértices queda fijado en el constructor
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

		// Eliminación de una arista, en O(1)
		public void eliminarArista(int i, int j)
		{
			verificarVertice(i, "eliminar una arista");
			verificarVertice(j, "eliminar una arista");

			_vecinos.get(i).remove(j);
			_vecinos.get(j).remove(i);
		}
		
		// Grado de un vértice (cantidad de vecinos), en O(1)
		public int grado(int i)
		{
			return vecinos(i).size();
		}
		
		// Conjunto de vecinos de un vértice, en O(1)
		@SuppressWarnings("unchecked")
		public Set<Integer> vecinos(int i)
		{
			verificarVertice(i, "consultar los vecinos");
			return (Set<Integer>) _vecinos.get(i).clone();
		}
			
		// Lanza una excepción si el índice v está fuera de rango para los vértices
		private void verificarVertice(int v, String accion)
		{
			if( v<0 || v>=vertices() )
				throw new IllegalArgumentException("Se intentó " + accion + " con un índice inexistente! vertice = " + v);
		}

		// Lanza una excepción si los índices son iguales
		private void verificarDistintos(int i, int j, String accion)
		{
			if( i == j )
				throw new IllegalArgumentException("Se intentó " + accion + " con los dos vértices iguales! vertice = " + i);
		}
		
		// Cantidad de vértices
		public int vertices()
		{
			return _vecinos.size();
		}
	}

