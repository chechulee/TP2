package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Grafo 
{
	//Implementación de Grafo mediante lista de vecinos 
	private int _vertices;
	private ArrayList <Set<Integer>> _vecinos;
	
	public Grafo(int n)
	{
		if (n<0)
			throw new IllegalArgumentException("Cantindad de vertices negativo " + n);
		
		_vertices = n;
		_vecinos = new ArrayList<Set<Integer>>();
		inicializarVecinos();
	}

	private void inicializarVecinos() {
		for (int i = 0; i < vertices() ;i++)
		{
			_vecinos.add(new HashSet<Integer>());			
		}
	}
	
	public void setArista(int i, int j)
	{
		chequeoExtremo(i);	
		chequeoExtremo(j);
		
		chequeoMismoVertice(i, j);
		_vecinos.get(i).add(j);
		_vecinos.get(j).add(i);
	}

	public void deleteArista(int i, int j)
	{
		chequeoExtremo(i);	
		chequeoExtremo(j);

		chequeoMismoVertice(i, j);
		_vecinos.get(i).remove(j);
		_vecinos.get(j).remove(i);
	}
	
	public boolean isArista(int i, int j)
	{
		chequeoExtremo(i);	
		chequeoExtremo(j);

		return _vecinos.get(i).contains(j);
	}
	
	public int vertices()
	{
		return _vertices;
	}
	
	public int grado(int v)
	{
		chequeoExtremo(v);	
		return _vecinos.get(v).size();
	}
	
	public Set<Integer> vecinos(int v)
	{
		chequeoExtremo(v);
		return _vecinos.get(v);
	}
	
	@Override
	public String toString() {
		return "Grafo [_vertices=" + _vertices + ", _vecinos=" + _vecinos + "]";
	}

	public boolean esConexo()
	{
		boolean[] marcados = BFS(0);
		
		return todosVisitados(marcados);
	}

	private boolean[] BFS(int inicio) 
	{
		boolean[] marcados = new boolean[vertices()];
		Queue<Integer> l = new LinkedList<Integer>();
		
		l.offer(inicio);
		
		while (l.isEmpty() == false)
		{
			Integer vertice = l.poll();
			marcados[vertice] = true;
			
			for (Integer v :vecinos(vertice))
				if (marcados[v] == false && l.contains(v) == false)
					l.offer(v);
		}
		return marcados;
	}
	
	private boolean todosVisitados(boolean[] marcados) 
	{
		boolean ret = true;
		
		for (int i=0; i<vertices(); ++i)
			ret = ret && marcados[i];
		
		return ret;
	}

	private void chequeoMismoVertice(int i, int j) 
	{
		if (i==j)
			throw new IllegalArgumentException("No puedo poner el mismo vertice " + i);
	}

	private void chequeoExtremo(int i) 
	{
		if (i<0 || i>=vertices())
			throw new IllegalArgumentException("Vertice invalido " + i);
	}
}














