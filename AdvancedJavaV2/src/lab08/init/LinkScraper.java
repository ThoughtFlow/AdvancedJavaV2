package lab08.init;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import lab.util.Util;

public class LinkScraper {
	
	/**
	 * Single-threaded url scraping - implement with executor service.
	 * 
	 * @param urls A list of urls to scrape.
	 * @return A map of scraped urls for all sites and their occurrence count.
	 * @throws InterruptedException Thrown upon a thread error.
	 * @throws ExecutionException Thrown upon an error in the callable.
	 */
	private static Map<String, Integer> invoke(List<String> urls) throws InterruptedException, ExecutionException {
		
		// Change this to executor service with callback and futures.
		// 1) Choose the appropriate executor service and mind the pool size
		// 2) Use submit/invoke, call and future, then shutdown the pool.
		Map<String, Integer> map = new HashMap<>();
		for (String nextUrl : urls) {
		   List<String> hrefs = Util.scrapeHrefs(nextUrl);
		   Map<String, Integer> nextMap = Util.catalog(hrefs);
		   map = Util.merge(map, nextMap);
		}
		
		return map;
	}
	
	public static void main(String... args)  {
		
		List<String> urls = Arrays.asList(
										  "https://www.google.com",
										  "https://www.youtube.com",
										  "https://www.facebook.com",
								          "https://www.wikipedia.org",
										  "https://www.spotify.com",
										  "https://www.yahoo.com",
										  "https://www.linkedin.com",
										  "https://www.twitter.com",
										  "https://www.ebay.com",
										  "https://www.apple.com");



		try {
			Map<String, Integer> results = invoke(urls);
			
			for (String nextKey : results.keySet()) {
				System.out.println(nextKey + ": " + results.get(nextKey));
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}