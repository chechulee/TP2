package logica;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Solver {
	private ArrayList<DatosLocalidad> conexiones;
	Grafo grafo;
	AGM arbolGM;
	
	public Solver(ArrayList c){
		this.conexiones = c;
		grafo = new Grafo(c.size());
		generarTodasLasAristasDelGrafo();
		arbolGM = new AGM(grafo);
		
	}
	
	private void generarTodasLasAristasDelGrafo(){
		for (int i = 0; i < conexiones.size(); i++){
			for(int j = i+1; j < conexiones.size(); j++){
				grafo.agregarArista(i,j);
				
			}
		}
	}
	
	public  Coordinate obtenerCoordenada(Integer pos){
		Coordinate coordenada = new Coordinate(conexiones.get(pos).latitud, conexiones.get(pos).longitud);
		return coordenada;
	}
	
	public String obtenerLocalidad(int pos){
		String localidad = conexiones.get(pos).Localidad;
		return localidad;
	}
	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
	
  private Double calcularCosto(String loc1, String loc2){
	  return 1.5;
  }
	
	
}
