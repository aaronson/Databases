package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.touchCount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ubadb.core.common.Page;
import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.PageReplacementStrategy;
import ubadb.core.exceptions.PageReplacementStrategyException;

public class TouchCountReplacementStrategy implements PageReplacementStrategy {

	public List<TouchCountBufferFrame> hotList = new ArrayList<>();
	public List<TouchCountBufferFrame> coldList = new ArrayList<>();

	@Override
	public BufferFrame findVictim(Collection<BufferFrame> bufferFrames)
			throws PageReplacementStrategyException {

		// The list given to us is already ordered by the buffer pool, we only
		// need to remove the first unpinned
		for (BufferFrame frame : bufferFrames) {
			if (frame.canBeReplaced()) {
				return frame;
			}
		}

		throw new PageReplacementStrategyException(
				"No page can be removed from pool");

	}

	@Override
	public BufferFrame createNewFrame(Page page) {
		return new TouchCountBufferFrame(page);

	}

}
