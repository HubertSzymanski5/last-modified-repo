package pl.szymanski.hubert.lastmodifiedrepo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitHubMapper implements Mapper {

    //--- FIELDS ---//

    private List<Repo> repoList;


    //--- INTERFACE METHODS ---//

    @Override
    public List<Repo> getRepoList( String urlString ) {
        // load Json from github
        try {
            // urls variables
            int i = 1;
            URL allegroGitRepo = new URL(urlString + "?page=" + i);

            // create Jackson mapper
            ObjectMapper mapper = new ObjectMapper();

            // create repoList Object
            repoList = new ArrayList<>();

            // read first page
            List<Repo> onePageRepoList = Arrays.asList(mapper.readValue(allegroGitRepo, Repo[].class));

            // iterate through pages
            while( onePageRepoList.size() != 0 ) {
                // add all elements to the repoList
                repoList.addAll(onePageRepoList);

                // increase page counter
                i++;

                // change URL to get next page
                allegroGitRepo = new URL( urlString + "?page=" + i);

                // load next page
                onePageRepoList = Arrays.asList(mapper.readValue(allegroGitRepo, Repo[].class));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return this.repoList;
    }


}
