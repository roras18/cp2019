package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedMapExecutor
{	
	public static void main()
	{
		List<Path> paths = new ArrayList<>();
		
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new ConcurrentHashMap<>();
		
		final int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool( cores );
		
		for( Path path : paths ) {
			executor.submit( () -> computeOccurrences( path, occurrences ) );
		}
		
		try {
			executor.shutdown();
			executor.awaitTermination( 1L, TimeUnit.DAYS );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
//		for( String word : occurrences.keySet() ) {
//			 System.out.println( word + ": " + occurrences.get( word ) );
//		}
	}
	
	private static void computeOccurrences( Path path, Map<String, Integer> occurrences )
	{
		try {
			Files.lines( path ).forEach(
				line -> {
					for( String word : line.split( " " ) ) {
						occurrences.compute( word, ( w, times ) -> {
							if ( times == null ) {
								return 1;
							} else {
								return times + 1;
							}
						} );
//						synchronized( occurrences ) {
//							if ( occurrences.containsKey( word ) ) {
//								occurrences.put( word, occurrences.get( word ) + 1 );
//							} else {
//								occurrences.put( word, 1 );
//							}
//						}
					}
				}
			);
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
