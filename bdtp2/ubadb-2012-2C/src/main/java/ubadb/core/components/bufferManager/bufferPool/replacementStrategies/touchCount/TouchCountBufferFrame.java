package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.touchCount;

import ubadb.core.common.Page;
import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.exceptions.BufferFrameException;

public class TouchCountBufferFrame extends BufferFrame {
	
	int count;

	public TouchCountBufferFrame(Page page) {
		super(page);
		count = 0;
	}
	
	@Override
	public void pin(){
		super.pin();
		count++;
	}
	
	@Override
	public void unpin() throws BufferFrameException{
		super.unpin();
		count++;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
