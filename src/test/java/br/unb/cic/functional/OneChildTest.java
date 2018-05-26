package br.unb.cic.functional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.unb.cic.rtgoretoprism.generator.kl.AgentDefinition;


public class OneChildTest {

	/**
	 * Invalid test case.
	 * 
	 * Goals and/or tasks with only one child and wrong runtime annotation
	 * 
	 * */
	
	private AgentDefinition agentDefinition;
	
	@Test
	public void TestCase9() {
		String goalName = "G1: one child [try(T1)?skip:T2]";
		String[] elementsName = {"T1: one child", "1", "1"};
		
		//Add elements to new register
		InformationRegister[] info = new InformationRegister[elementsName.length/3];
		createRegister(elementsName, info);
	
		try{
			
			//Create CRGM java model
			CRGMTestProducer producer = new CRGMTestProducer(2, 0, 1, "EvaluationActor");
			agentDefinition = producer.generateCRGM(goalName, info, null);

			//Run Goda
			producer.run(agentDefinition);
			
			fail("No exception found.");
						

		}catch(Exception e){
			assertNotNull(e);
			e.printStackTrace();
		}
	}

	@Test
	public void TestCase10() {

		String goalName = "G1: task error";
		String[] elementsName = {"T1: one child [T1.1#T1.2]", "1", "1", 
				"T1.1: leaf", "1", "2"};
		
		//Add elements to new register
		InformationRegister[] info = new InformationRegister[elementsName.length/3];
		createRegister(elementsName, info);
		
		try{
			
			//Create CRGM java model
			CRGMTestProducer producer = new CRGMTestProducer(3, 0, 1, "EvaluationActor");
			agentDefinition = producer.generateCRGM(goalName, info, null);
			
			//Run Goda
			producer.run(agentDefinition);
			
			fail("No exception found.");
		}catch(Exception e){
			assertNotNull(e);
			e.printStackTrace();
		}
	}
	
	private void createRegister(String[] elementsName, InformationRegister[] infoArray) {	
		int index = 0;
		int index_infoArray = 0;
		for (InformationRegister info : infoArray) {
			info = new InformationRegister();
			info.createRegister(elementsName, index);
			index = index + 3;
			
			infoArray[index_infoArray] = info;
			index_infoArray++;
		}
	}
}
