import java.util.*;
public class Week5Special {
    private static Map<Integer,String> tasks=new HashMap<>();
    private static Map<String, String> status=new HashMap<String, String>();
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("demosntrating a to-do list application");
       while(true){
           System.out.println("choose your opeartions(press 0 to exit)");
           System.out.println("1.add tasks");
           System.out.println("2.delete tasks");
           System.out.println("3.update tasks");
           System.out.println("4.view tasks");
           System.out.println("5.view status");
           System.out.println("0.to exit");
           int ch=sc.nextInt();
           if(ch==0){
               System.out.println("you chose to exit");
               return;
           }
           switch(ch){
               case 1->add_tasks();
               case 2->delete_tasks();
               case 3->update_tasks();
               case 4->view_tasks();
               case 5->view_status();

           }
       }
    }
    public static void add_tasks(){
        System.out.println("enter no.of tasks");
        int n=sc.nextInt();
        sc.nextLine();
        for(int i=1;i<=n;i++){
            System.out.println("enter task :"+i);
            tasks.put(i,sc.nextLine());
        }
        System.out.println("all tasks have been added");
    }
    public static void view_tasks(){
        if(tasks.isEmpty()){
            System.out.println("no tasks to view");
            return;
        }
        System.out.println("tasks are:");
        for(Map.Entry<Integer,String> entry:tasks.entrySet()){
            System.out.println(entry.getKey()+"."+entry.getValue());
        }
    }
    public static void delete_tasks(){
        System.out.println("enter task number to delete");
        int task_no=sc.nextInt();
        if(tasks.containsKey(task_no)){
            tasks.remove(task_no);
            System.out.println("task deleted successfully");
        }else{
            System.out.println("task not found");
        }
    }
    public static void update_tasks(){
        tasks.forEach((key,value) -> status.put(value,"pending"));
        sc.nextLine();
        System.out.println("enter the task to upadate");
        String task=sc.nextLine();
        if(status.containsKey(task)){
            System.out.println("enter the new status");
            String new_status=sc.nextLine();
            status.put(task,new_status);
            System.out.println("task updated successfully");
        }

    }
    public static void view_status(){
        if(status.isEmpty()){
            System.out.println("no tasks are addes");
            return;
        }
        System.out.println("status are:");
        for(Map.Entry<String,String> entry:status.entrySet()){
            System.out.println(entry.getKey()+"."+entry.getValue());
        }
    }
}
