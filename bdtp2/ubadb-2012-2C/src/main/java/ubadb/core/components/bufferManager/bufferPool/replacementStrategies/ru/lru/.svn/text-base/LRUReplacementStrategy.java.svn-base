package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.ru.lru;

import java.util.Collection;
import java.util.Date;

import ubadb.core.common.Page;
import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.PageReplacementStrategy;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.ru.RUBufferFrame;
import ubadb.core.exceptions.PageReplacementStrategyException;

public class LRUReplacementStrategy implements PageReplacementStrategy {

	@Override
	public BufferFrame findVictim(Collection<BufferFrame> bufferFrames)
			throws PageReplacementStrategyException {
		RUBufferFrame victim = null;
		Date oldestUsage = null;
		
		for(BufferFrame bufferFrame : bufferFrames)
		{
			RUBufferFrame lruBufferFrame = (RUBufferFrame) bufferFrame; //safe cast as we know all frames are of this type
			if(lruBufferFrame.canBeReplaced() && (oldestUsage == null || lruBufferFrame.getLastUsage().before(oldestUsage)))
			{
				victim = lruBufferFrame;
				oldestUsage = lruBufferFrame.getLastUsage();
			}
		}
		
		if(victim == null)
			throw new PageReplacementStrategyException("No page can be removed from pool");
		else
			return victim;
	}

	@Override
	public BufferFrame createNewFrame(Page page) {
		return new RUBufferFrame(page);
	}

}
