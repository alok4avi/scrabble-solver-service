# scrabble-solver-service
Scrabble solver REST service

Requirements:
  Requires Maven 3 or newer and Java JDK 8 or newer.

To build:
  mvn clean package

To run tests and coverage report:
  mvn clean verify


To run:
  mvn java -jar scrabble-solver-service-0.0.1-SNAPSHOT.jar

URL format:
    http://localhost:8080/words/<letters>

Example usage:
    > curl http://localhost:8080/words/hat
    ["hat","ah","ha","th","at","a"]

    > curl http://localhost:8080/words/zzz
    []
	
To see api documentation:
	http://localhost:8080/swagger-ui.html
	
	
Memory Usage & Time Complexity: 

In this appplication I am loading the words from the 'words.txt' file at the start-up of the service so that by the time 
first request starts coming all the dictionary words are already loaded into the memory which means response will be faster. 
My algorithm is basically revolves around the use of Trie data structure, which is an efficient information retrieval data structure.  
The amount of time it takes to create a Trie is tied directly to how many words/keys the Trie contains, and how long those 
keys could potentially be. The worst-case runtime for creating a Trie is a combination of M, the length of the longest key 
in the Trie, and N, the total number of keys in the Trie. Thus, the worst case runtime of creating a Trie is O(MN) and
this is the reason I am doing this at the start-up. The time complexity of searching and inserting from a trie depends on 
the length of the word A that’s being searched for or inserted and the number of total words, N, making the runtime of these 
operations O(AN).

The downside of using Trie is that is takes up a lot of memory and space with empty (null) pointers. Trie would start grow
in size, and with each node that was added, an entire array containing 26 null pointers would have to be initialized as well. 
For longer words, those empty references would probably never get filled up. But also for Trie each time we add a word’s letter,
we know that we’ll only ever have to look at 26 possible indexes in a node’s array. Even though 26 seems like a lot,
for our computers, it’s really not that much space.
