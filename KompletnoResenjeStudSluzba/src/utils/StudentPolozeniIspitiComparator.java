package utils;

import java.util.Comparator;

import model.IspitnaPrijava;
import model.Student;

public class StudentPolozeniIspitiComparator implements Comparator<Student>{

	int direction = 1;
	
	public StudentPolozeniIspitiComparator(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Student ob1, Student ob2) {
		int retVal = 0;
		if(ob1!= null && ob2!=null){
			int brojacPolozenihPredmetaSt1=0;
			for (IspitnaPrijava isp : ob1.getIspitnePrijave()) {
				if(isp.sracunajOcenu()>5){
					brojacPolozenihPredmetaSt1++;
				}
			}
			int brojacPolozenihPredmetaSt2=0;
			for (IspitnaPrijava isp : ob2.getIspitnePrijave()) {
				if(isp.sracunajOcenu()>5){
					brojacPolozenihPredmetaSt2++;
				}
			}
			retVal = brojacPolozenihPredmetaSt1-brojacPolozenihPredmetaSt2;
		}
		return retVal * direction;
	}
}