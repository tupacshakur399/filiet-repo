package classifier.implementations;

import java.io.File;

import model.Sentence;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Instance;
import weka.core.Instances;
import classifier.InstanceBuilder;

public class KNNClassifierImpl implements ClassifierInterface {
	
	private SerializedClassifier classifier;
	private String path;

	
	public KNNClassifierImpl(String path){
		this.path = path;
		System.out.println("KNNClassifier Path: " + path );
		init();
		
	}
	
	public void init(){
		classifier = new SerializedClassifier();
		classifier.setModelFile(new File(path));
	}
	
	@Override
	public String classify(Sentence text) {
		// TODO Auto-generated method stub
		InstanceBuilder builder = new InstanceBuilder();
		Instances dataset = builder.CreateDataset(text);
		System.out.println("Dataset Variable: " + dataset.classAttribute().name());
		Instance data = builder.CreateInstance(text,dataset);
		double value = -1;
		String label = null;
		try {
			value = classifier.classifyInstance(data);
			label = dataset.classAttribute().value((int) value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return label;
	}

}