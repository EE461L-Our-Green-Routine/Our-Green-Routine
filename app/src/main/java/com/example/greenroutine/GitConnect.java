package com.example.greenroutine;
import org.kohsuke.github.*;
import java.io.IOException;
import java.util.List;

/**GitConnect Bridge Class
 *
 */
public class GitConnect {

    static int getGitIssues(String user){
        int count = -1;
        try {
            GitHub gh = GitHub.connectAnonymously();
            GHRepository ogm = gh.getRepository("mpontikes/Our-Green-Routine");
            List<GHIssue> mp = ogm.listIssues(GHIssueState.ALL).asList();
            count = 0;
            for(GHIssue cmt : mp){
                if(cmt.getUser().getLogin().equals(user)){
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("ERR");
        }
        return count;
    }
    static int getGitCommit(String user){
        int count = -1;
        try {
            GitHub gh = GitHub.connectAnonymously();
            GHRepository ogm = gh.getRepository("mpontikes/Our-Green-Routine");
            List<GHCommit> mp = ogm.listCommits().asList();
            count = 0;
            for(GHCommit cmt : mp){
                if(cmt.getAuthor().getLogin().equals(user)){
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("ERR");
        }
        return count;
    }

    static int getUnitTests(String user){
        return -1;
    }
}
