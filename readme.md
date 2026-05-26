## Saving Data/ Remember old chats 
### ChatMemory (i)
##### Has 3 methods:
- void add(String conversationId, List<Message> messages)
- List<Message> get(String conversationId);
- void clear(String conversationId);

##### Chat Memory interface is responsible for: 
1. what to store
2. strategies
- Keep last N messages 
- time period
- within the token limit 

### ChatMemoryRepository  (i)
1. Storage Engine
2. To store Data
3. responsibale for saving reading data
- We have inMorory implemation for this class but can create custom implemation to save sata in db.

- We can add multiple advisors
- InMemoryChatMemoryRepository implements ChatMemoryRepository (Stores data in Map<String, List<Message>> chatMemoryStore = new ConcurrentHashMap<>();)
- String as key is very important to keep data separate for different user (conversation ID) and important for session management
- 