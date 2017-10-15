package logica;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class Test {
	private JMapViewer miMapa;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		miMapa = new JMapViewer();
		miMapa.setZoomContolsVisible(false);
		
		miMapa.setDisplayPositionByLatLon( -38, -64, 4);
		frame.setContentPane(miMapa);
		Prueba prueba = new Prueba();
		DatosLocalidad d ;
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		

		for (int i = 0 ; i < prueba.conexiones.size(); i++){
			d = (DatosLocalidad) prueba.conexiones.get(i);
			agregarLocalidadMapa(d.getLat(),d.getLon(), d.getLocalidad());
		}
		
		Solver solver = new Solver(prueba.conexiones);
		for(Integer i = 0; i <  prueba.conexiones.size() ; i++){
	         for (Integer element : solver.grafo.vecinos(i)){
	        	 coordenadas.add(solver.obtenerCoordenada(i));
	         coordenadas.add(solver.obtenerCoordenada(element));
	         }
		}
		dibujarArista(coordenadas);
	}
	
	public void agregarLocalidadMapa(Double latitud, Double longitud, String localidad){
		Coordinate coordenada = new Coordinate(latitud,longitud);
		MapMarker marcador = new MapMarkerDot(localidad, coordenada);
		miMapa.addMapMarker(marcador);
    }
	
	public void dibujarArista(ArrayList<Coordinate> coordenadas){
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		miMapa.addMapPolygon(polygon);
	}
}
