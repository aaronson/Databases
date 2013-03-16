package ubadb.core.components.bufferManager.bufferPool.pools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ubadb.core.common.Page;
import ubadb.core.common.PageId;
import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.components.bufferManager.bufferPool.BufferPool;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.PageReplacementStrategy;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.touchCount.TouchCountBufferFrame;
import ubadb.core.exceptions.BufferPoolException;

public class TouchCountBufferPool implements BufferPool {

	private Map<PageId, BufferFrame> framesMap;
	private PageReplacementStrategy pageReplacementStrategy;
	private final int maxBufferPoolSize;
	private List<PageId> orderedList;

	public TouchCountBufferPool(int maxBufferPoolSize,
			PageReplacementStrategy pageReplacementStrategy) {
		this.maxBufferPoolSize = maxBufferPoolSize;
		this.pageReplacementStrategy = pageReplacementStrategy;
		this.framesMap = new HashMap<PageId, BufferFrame>(maxBufferPoolSize);
		this.orderedList = new ArrayList<>();
	}

	public boolean isPageInPool(PageId pageId) {
		return framesMap.containsKey(pageId);
	}

	public BufferFrame getBufferFrame(PageId pageId) throws BufferPoolException {
		if (isPageInPool(pageId))
			return framesMap.get(pageId);
		else
			throw new BufferPoolException(
					"The requested page is not in the pool");
	}

	public boolean hasSpace(PageId pageToAddId) {
		return countPagesInPool() < maxBufferPoolSize;
	}

	public BufferFrame addNewPage(Page page) throws BufferPoolException {
		if (!hasSpace(page.getPageId()))
			throw new BufferPoolException("No space in pool for new page");
		else if (isPageInPool(page.getPageId()))
			throw new BufferPoolException("Page already exists in the pool");
		else {
			// Add it to pool
			BufferFrame bufferFrame = pageReplacementStrategy
					.createNewFrame(page);
			PageId id = page.getPageId();
			framesMap.put(id, bufferFrame);
			orderedList.add(orderedList.size() / 2, id);
			migrate();

			return bufferFrame;
		}
	}

	private void migrate() {
		int toTraverse = orderedList.size() % 2 == 0 ? (orderedList.size() / 2) - 1
				: orderedList.size() / 2;
		int half = toTraverse;

		// We have to traverse only till the end of the original cold region, if
		// we remove one element from it, the the original is shrinked
		for (int i = 0; i <= toTraverse; i++) {
			if (((TouchCountBufferFrame) framesMap.get(orderedList.get(i)))
					.getCount() > 2) {

				PageId id = orderedList.remove(i);
				orderedList.add(id);
				((TouchCountBufferFrame) framesMap.get(id)).setCount(0);

				// removed one, so substract one from i and toTraverse
				i--;
				toTraverse--;
			}
		}

		// Now we need to reset the count of the ones that were moved from the
		// hot region to the cold region, those are the ones that range from the
		// toTraverse number till the original half number
		for (int i = toTraverse + 1; i <= half; i++) {
			((TouchCountBufferFrame) framesMap.get(orderedList.get(i)))
					.setCount(1);
		}

	}

	public void removePage(PageId pageId) throws BufferPoolException {
		if (isPageInPool(pageId)) {
			framesMap.remove(pageId);
			// Remove it in case it's still in the ordered list
			orderedList.remove(pageId);
		} else
			throw new BufferPoolException("Cannot remove an unexisting page");
	}

	public BufferFrame findVictim(PageId pageIdToBeAdded)
			throws BufferPoolException {
		try {
			// Execute a migration, then find the victim
			migrate();
			return pageReplacementStrategy.findVictim(sortByList());
		} catch (Exception e) {
			throw new BufferPoolException(
					"Cannot find a victim page for removal", e);
		}
	}

	// Since we use a list of pageIds and the strategy method requires a
	// collection of frames, we need to use the map to get the frames and sort
	// it as the list says
	private Collection<BufferFrame> sortByList() {
		Collection<BufferFrame> sorted = new ArrayList<BufferFrame>();

		for (PageId id : orderedList) {
			sorted.add(framesMap.get(id));
		}
		return sorted;
	}

	public int countPagesInPool() {
		return framesMap.size();
	}

}
