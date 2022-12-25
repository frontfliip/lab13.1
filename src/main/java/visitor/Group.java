package visitor;

import java.util.*;

public class Group<T> extends Task<T> {
    public String groupUuid;
    private List<Task<T>> tasks;

    private Map<String, String> headers;

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        groupUuid = UUID.randomUUID().toString();
        for (Task<T> task: tasks) {
            task.freeze();
        }
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        VisitorImpl visitor = new VisitorImpl(this.getId());
        for (Task<T> task: tasks) {
            task.apply(arg);
            task.stamp(visitor);
        }
    }

    protected void setHeader(String header, String headerValue) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(header, headerValue);
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    @Override
    public void stamp(Visitor v) {
        this.setHeader("groups", v.onGroup(this).get("groups"));
    }
}
