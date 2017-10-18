/**
 * 
 */
package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import sim.SimulationEnvironment;
import sampling.SamplingCollection;
import sampling.SimulationTimeSeries;
import sampling.StatisticSampling;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.figures.IXYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.ToolbarArmedXYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.ErrorBarType;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;

import simulator.AbCSystem;
import simulator.AverageDeliveryTime;
import simulator.AverageInputQueueSize;
import simulator.AverageMessageInterval;
import simulator.AverageWaitingQueueSize;
import simulator.MaxDeliveryTime;
import simulator.MaxMessageInterval;
import simulator.MinDeliveryTime;
import simulator.MinMessageInterval;
import simulator.NumberOfDeliveredMessages;
import simulator.SingleServerFactory;
import simulator.TravellingMessages;

public class RunSingleServerSimulation {

	public static void main(String[] argv) throws FileNotFoundException {
		final Shell shell = new Shell();
		shell.setSize(600, 550);
		shell.open();

//		// use LightweightSystem to create the bridge between SWT and draw2D
		final LightweightSystem lws = new LightweightSystem(shell);
//
//		// create a new XY Graph.
		IXYGraph xyGraph = new XYGraph();
		ToolbarArmedXYGraph toolbarArmedXYGraph = new ToolbarArmedXYGraph(xyGraph);
		xyGraph.setTitle("Performance Evaluation of the Centralized Infrastructure");
//		// set it as the content of LightwightSystem
		lws.setContents(toolbarArmedXYGraph);
//
//		// Configure XYGraph
				xyGraph.getPrimaryXAxis().setShowMajorGrid(true);
				xyGraph.getPrimaryYAxis().setShowMajorGrid(true);
				xyGraph.getPrimaryXAxis().setAutoScale(true);
				xyGraph.getPrimaryYAxis().setAutoScale(true);
		FileOutputStream fout=new FileOutputStream("mfile.txt");
		
		// change simluation Time
				double simulationTime = 2000;
				
				// choose the number of samples
				int samples = 100;
				
				// choose the number of replications 
				int replications = 10;
				
				// choose the system configurations
		SingleServerFactory factory = new SingleServerFactory(620,62, 31,(x,y) -> 15.0 , x -> 1000.0 , x -> 1.00 );
		
		// the measures we consider 
		SimulationEnvironment<AbCSystem> env = new SimulationEnvironment<>(factory);		
		StatisticSampling<AbCSystem> averageDeliveryTime = new StatisticSampling<>(samples, simulationTime/samples, new AverageDeliveryTime());
		StatisticSampling<AbCSystem> maxDeliveryTime = new StatisticSampling<>(samples, simulationTime/samples, new MaxDeliveryTime());
		StatisticSampling<AbCSystem> minDeliveryTime = new StatisticSampling<>(samples, simulationTime/samples, new MinDeliveryTime());
		StatisticSampling<AbCSystem> numberOfDeliveredMessages = new StatisticSampling<>(samples, simulationTime/samples, new NumberOfDeliveredMessages());
	
//		StatisticSampling<AbCSystem> averageWaitingSize = new StatisticSampling<>(samples, simulationTime/samples, new AverageWaitingQueueSize());
		StatisticSampling<AbCSystem> averageTimeInterval = new StatisticSampling<>(samples, simulationTime/samples, new AverageMessageInterval());
		StatisticSampling<AbCSystem> maxTimeInterval = new StatisticSampling<>(samples, simulationTime/samples, new MaxMessageInterval());
		StatisticSampling<AbCSystem> minTimeInterval = new StatisticSampling<>(samples, simulationTime/samples, new MinMessageInterval());
//		StatisticSampling<AbCSystem> numberOfMessages = new StatisticSampling<>(2001, 0.5, new NumberOfDeliveredMessages());
//		StatisticSampling<AbCSystem> sentMessages = new StatisticSampling<>(2001, 0.5, new TravellingMessages());
		
		// select the measures for simulation 
		SamplingCollection<AbCSystem> collection = new SamplingCollection<>( averageDeliveryTime, maxDeliveryTime, minDeliveryTime, numberOfDeliveredMessages, averageTimeInterval,minTimeInterval,maxTimeInterval);
		env.setSampling(collection);
		env.simulate(replications,simulationTime);
		//averageDeliveryTime.printTimeSeries(new PrintStream(fout));
		LinkedList<SimulationTimeSeries> series=new LinkedList<>();
		series.addAll(averageDeliveryTime.getSimulationTimeSeries(replications));
		series.addAll(maxDeliveryTime.getSimulationTimeSeries(replications));
		series.addAll(minDeliveryTime.getSimulationTimeSeries(replications));
//		series.addAll(numberOfDeliveredMessages.getSimulationTimeSeries(replications));
		series.addAll(averageTimeInterval.getSimulationTimeSeries(replications));
		series.addAll(maxTimeInterval.getSimulationTimeSeries(replications));
		series.addAll(minTimeInterval.getSimulationTimeSeries(replications));
		//series.addAll(averageWaitingSize.getSimulationTimeSeries(replications));
		LinkedList<Trace> traces=new LinkedList<>();
		LinkedList<SimulationTrace> simtraces=new LinkedList<>();
		for (SimulationTimeSeries ser: series) {
			simtraces.add(new SimulationTrace(ser));
		}
		for (SimulationTrace strace: simtraces) {
			Trace trace = new Trace(strace.getName(), xyGraph.getPrimaryXAxis(), xyGraph.getPrimaryYAxis(), strace ) ;
			trace.setErrorBarEnabled(true);
			trace.setYErrorBarType(ErrorBarType.BOTH);
			trace.setXErrorBarType(ErrorBarType.NONE);
			xyGraph.addTrace( trace );
			traces.add(trace);
		}
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	//	SimulationTrace simTrace=new SimulationTrace(averageDeliveryTime.getSimulationTimeSeries(10));
		//numberOfDeliveredMessages.printTimeSeries(new PrintStream(fout));;
		
	}
	
}
