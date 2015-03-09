package support.languagemodeller;

import java.io.IOException;
import java.util.Iterator;

import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;
import cmu.arktweetnlp.Twokenize;

public class Driver {
	public static void main(String[] args){
		NGramModeller model = new NGramModeller();
		String path = "./resources/tweets/ruby-datasets/combined-d.csv";
		String charngram = "./resources/model/ngram/ruby-ngram-d";
		String save = "./resources/model/word/ruby-word-d";
		int top = 30;
			
//		try {
//			model.CharNGram(2,top,charngram, path);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		String dataset = "./resources/tweets/ruby-datasets/original/ruby-combined-no-o.csv";
		String categoryDataset = "./resources/tweets/ruby-datasets/original/combined-ca.csv";
		String saveResults = "./resources/model/TFIDF-Scores/ca-TFIDF-100-rate10.txt";
		
		try {
			DocumentFrequency documentDataset = new DocumentFrequency(dataset);
			DocumentFrequency documentCategory = new DocumentFrequency(categoryDataset);
			
			WeightScorer tfidfScores = new WeightScorer(documentCategory, documentDataset);
			tfidfScores.computeWeights();
			//tfidfScores.saveResults(saveResults);
			int size = (int) (tfidfScores.computeSize() * 0.10);
			tfidfScores.getTop(saveResults,size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
