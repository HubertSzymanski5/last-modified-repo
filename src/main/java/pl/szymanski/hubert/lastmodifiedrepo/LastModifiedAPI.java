package pl.szymanski.hubert.lastmodifiedrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LastModifiedAPI {

    //--- FIELDS ---//

    private Manager repoManager;


    //--- CONSTRUCTORS ---//

    @Autowired
    public LastModifiedAPI(Manager repoManager) {
        this.repoManager = repoManager;
    }


    //--- API ---//

    @GetMapping("/last-modified-name")
    public String getLastModifiedRepoName() {
        return repoManager.getLastModifiedName();
    }

    @GetMapping("/last-modified")
    public Repo getLastModifiedRepo() {
        return repoManager.getLastModified();
    }


    // this API returns repos without updating list
    // so its possible to check output
    @GetMapping("/repos")
    public List<Repo> getRepos() {
        return repoManager.getRepoList();
    }

}
