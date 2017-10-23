/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 3/4/2017
* Description: This program will simulate the arrival and processing of tasks in a queue over time.
 */

public class TaskQueue {    
    /** queue of tasks, the task in front of the queue is being processed */
    private QueueInterface<Task> Q;
    /** current timer of the class */
    private int Time;
    /** time when the task in front of the queue needs to be removed,
     * as the transaction is complete */
    private int dequeueTime;
    /** number of tasks processed */
    private int tasks;
    /** total wait time. (for a task, the wait time is the time
     * difference between the moment the task entered the queue and
     * the time when it starts) */
    private int totalWaitTime;
    
    /** Constructor */
    public TaskQueue(){
	Time = 0;
	dequeueTime =-1;
	tasks = 0;
	totalWaitTime =0;
	Q = new QueueReferenceBased<Task>();
    }

    /** get the current time */
    public int getTime(){
	return Time;
    }

    /** add a task to the queue 
     * @param newTask new task to be handled by the queue.
     */
    public void add(Task newTask){
	tasks++;
	System.out.println(Time+ ": Task " + newTask.id 
			   + " enqueued (transaction time="
			   + newTask.transactionTime+")");
	// * TO COMPLETE *
	// enqueue the newTask
	// if the new task can start immediately, update the dequeue 
	// time and print out that the task is starting
    if (Q.isEmpty()){
    	dequeueTime = newTask.arrivalTime + newTask.transactionTime;
	    //System.out.println("dequeue time is: " + dequeueTime);
	    System.out.print(Time + ": " + "Task "+ newTask.id+" starts being processed.\n");
    }
	Q.enqueue(newTask);
    }
    
    /** get the number of tasks that have been present in the queue
     * @return number of tasks that entered the queue
     */
    public int getNumTasks(){
	return tasks;
    }
    /** get the current average wait time, i.e. the average time that
     * a task waited between the moment it entered the queue and the
     * moment when it starts to be processed
     * @return the average waiting time
     */
    public double getAvgWaitTime(){
	return (double) totalWaitTime/tasks;
    }

    /** tells whether the queue completed all the current task
     * @return return true when all the current tasks have been
     * performed (i.e. the queue is empty; false otherwise (i.e. the
     * queue is not empty) 
     */
    public boolean isComplete(){
	return Q.isEmpty();
    }
    /** manage the queue. When needed
     * <li> remove a completed task from the queue
     * <li> starts a new task
     * <li> process the current task
     */
    public void process() {
	System.out.print(Time + ": ");
	if (Q.isEmpty())
	    System.out.print("idle\n");
	if (Time==dequeueTime) {
	    // * TO COMPLETE *
	    // Remove task from queue and print out that it has been completed
		System.out.printf("The task " + Q.dequeue().id +" has been completed!\n");
	    if (!Q.isEmpty()) {
			// * TO COMPLETE *
			// Set dequeueTime by looking at the (but not removing) task at the front of the queue
		    dequeueTime = Time + Q.peek().transactionTime;
		    //System.out.println("dequeue time is: " + dequeueTime);
			// Set the startTime of this task to Time
		    Q.peek().startTime = Time;
			// Print out that next job starts processing
		    System.out.printf(Time + ": " + "Task "+ Q.peek().id+" starts being processed.\n");
			// Increment total wait time by the wait time for this task
		    totalWaitTime += Q.peek().startTime - Q.peek().arrivalTime;
	    }
	}
	else if (!Q.isEmpty())
	    System.out.print("Task "+ Q.peek().id+" is being processed,\n");
	
	System.out.println();
	Time++;
    }// end process
    
}
