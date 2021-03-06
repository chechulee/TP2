package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class AGM {
	 @Override
	public String toString() {
		return "AGM [arbolGM=" + arbolGM + "]";
	}

	ArrayList<PesoArista>  aristasConPesos;
	Grafo arbolGM;
	int cantAristas;
	int cantVertices;
	int ciclos;
	ArrayList<Integer>  visitados;

	
	public AGM( ArrayList pAristas, int cantV){
		aristasConPesos = pAristas;
		cantVertices = cantV;
		cantAristas = 0;
		visitados = new  ArrayList<Integer>();
		arbolGM = new Grafo(cantVertices);
	kruskal();
	}
	
	public void kruskal(){
		Double menorPeso;
		int vertice1, vertice2;
	while (aristasConPesos.size()!=0){
		    menorPeso = aristasConPesos.get(0).getPeso();
			vertice1 = aristasConPesos.get(0).aristas.a;
			vertice2 = aristasConPesos.get(0).aristas.b;
			
			if(!arbolGM.existeArista(vertice1, vertice2)){
				arbolGM.existeArista(vertice1,vertice2);
				 cantAristas ++;
			     //System.out.println("cantAristas" + vertice1 +" - "+ vertice2);
			  }
			
				if (grafoTieneCiclo()){
					arbolGM.eliminarArista(vertice1,vertice2);
					cantAristas--;
					aristasConPesos.remove(0);
				}else
					aristasConPesos.remove(0);
	
	}

	
}
	private boolean grafoTieneCiclo() {
	   if(this.ciclos > 0)
		   return true;
	   else
		   return false;
	}
}