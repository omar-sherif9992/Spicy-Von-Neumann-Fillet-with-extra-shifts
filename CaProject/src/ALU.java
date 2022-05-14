
public class ALU {
	private int output;
	private boolean zero;
	Processor processor;
	public ALU(Processor processor) {
		this.processor=processor;
		this.zero=false;
	}
	
	public void execute(int opcode, int rs, int rt,int immediate,int shamt) {
		if(opcode == 0) { //check for the zero register
			output=processor.getGprs()[rs-1].getData()+processor.getGprs()[rt-1].getData(); //add
		}
		else if(opcode == 1) {
			output=processor.getGprs()[rs-1].getData()-processor.getGprs()[rt-1].getData(); //sub
		}
		else if(opcode == 2) {
			output=processor.getGprs()[rs-1].getData()*immediate; //multimmediate
		}
		else if(opcode == 3) {
			output=processor.getGprs()[rs-1].getData()+immediate; //addi
		}
		else if(opcode == 4) {
			output=processor.getGprs()[rs-1].getData()-processor.getGprs()[rt-1].getData();//bne
			
		}
		else if(opcode == 5) {
			output=processor.getGprs()[rs-1].getData()&immediate; //andi
		}
		else if(opcode == 6) {
			output=processor.getGprs()[rs-1].getData()|immediate; //orI

		}
		else if(opcode == 7) {
			//jump do nothing here
		}
		else if(opcode == 8) {
			output=processor.getGprs()[rs-1].getData()<<shamt; //shiftleftlogical

		}
		else if(opcode == 9) {
			output=processor.getGprs()[rs-1].getData()>>shamt; //shiftrightlogical
		}
		else if(opcode == 10) {
			output=processor.getGprs()[rs-1].getData()+immediate; //loadword
		}

		else if(opcode == 11) {
			output=processor.getGprs()[rs-1].getData()+immediate; //storeword

		}

		zero= output == 0;
		System.out.println("@ ALU \nOutput: "+output + " Zero: "+ zero);

	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public boolean isZero() {
		return zero;
	}

	public void setZero(boolean zero) {
		this.zero = zero;
	}

	
}
