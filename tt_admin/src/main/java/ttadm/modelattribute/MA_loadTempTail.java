package ttadm.modelattribute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session")
public class MA_loadTempTail {

	List<Integer> tailIndex = new ArrayList<Integer>();

	public List<Integer> getTailIndex() {
		return tailIndex;
	}

	public void setTailIndex(List<Integer> tailIndex) {
		this.tailIndex = tailIndex;
	}
	
	
	
	
}
