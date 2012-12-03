package com.report.plugin.hyc.jira.reports;
 
import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.atlassian.jira.web.action.ProjectActionSupport;
import com.atlassian.jira.issue.*;
import com.atlassian.core.util.map.EasyMap;
import com.atlassian.jira.util.collect.MapBuilder;
import com.atlassian.crowd.embedded.api.User;
import java.util.*;


import java.util.Map;



public class MyReport extends AbstractReport {


final IssueManager manager;


public MyReport(final IssueManager pManager){
this.manager = pManager;



}



    public String generateReportHtml(ProjectActionSupport action, Map map) throws Exception {
       
    
     String enhancementRequestID = (String) map.get("EnhancementRequestID");

//generiert das objekt mit dem übergebenen schlüssel
     final Issue issue = manager.getIssueObject(enhancementRequestID);
     issue.getSubTaskObjects();
     //diese methode gibt den bearbeiter des Enhancement Requests aus-
     User assignee = issue.getAssigneeUser();
    final Issue subtask;
    // hier wird ein array von allen subtasks erstellt
    Object[] subtaskArray = issue.getSubTaskObjects().toArray();
   
   

   
    //Ich versuche hier aus einem subtask ein issue zu machen.
    String x = subtaskArray[0].toString();
    Issue y = manager.getIssueObject(x);
    Map<String, String> subtaskMap = getSubTasks(issue);
   
    String sub = getSubTaskID(issue).get(1).toString();
    manager.getIssueObject(sub).getIssueTypeObject();
   
   
     if(enhancementRequestID == null)
     return ("Fehler, keine UserStory ausgewählt.");
     else return (/*"die Aufgabe ist " + assignee + " zugeteilt \n" +*/ "Features: " + subtaskMap.keySet() + " Beschreibung der Features " + subtaskMap.values() + " Summe der Features: " + subtaskMap.size() +
     " Typ: " + issue.getIssueTypeObject().getName()
     + " Typ: " + manager.getIssueObject(sub).getIssueTypeObject().getName());

    
  // return descriptor.getHtml("view");
        
        
    }
 
    public Map getSubTaskID(Issue pIssue){
     Object[] subtasks = pIssue.getSubTaskObjects().toArray();
     Map<Integer, String> sub = new HashMap();
     for (int i = 0; i < subtasks.length; i++)
     {
    
     sub.put(i, subtasks[i].toString());
    
     }
    
     return sub;
    }
    /* methods have been merged in getSubTask
public Map getDescription(Map<Integer, String> map){
Map<Integer, String> description = new HashMap();
for(int i = 0; i < map.size(); i++){
description.put(i, manager.getIssueObject(map.get(i)).getDescription());
}
return description;
}
*/
    public Map getSubTasks(Issue pIssue){
     Object[] subtasks = pIssue.getSubTaskObjects().toArray();
     Map<String, String> sub = new HashMap();
     for (int i = 0; i < subtasks.length; i++)
     {
    
     sub.put(subtasks[i].toString(), manager.getIssueObject(subtasks[i].toString()).getDescription());
    
     }
    
     return sub;
    }
  
    
    
    
    
}