package edu.uncc.servlet;

public class Todo {

    private String todo;
    private String datetime;
    private String timeremaining;
    private String status;
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTodo(String todo) {
        this.todo = todo;
    }
 
    public String getTodo() {
        return todo;
    }

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTimeremaining() {
		return timeremaining;
	}

	public void setTimeremaining(String timeremaining) {
		this.timeremaining = timeremaining;
	}
    
    
}
