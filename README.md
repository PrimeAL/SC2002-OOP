# SC2002
Developing a Hospital Management System (HMS) with java

# Branches:
1. **TRY TO BRANCH EVERYTIME YOU WORK ON A NEW FEATURE**
2. **DO NOT PUSH TO MAIN**
3. To start, make sure you pull the latest changes from `main` branch
```
cd <your directory>
git checkout main
git status  -> check you are at main branch
git pull origin main
```

4. Then create a new branch with this format `<which_code-feature-name>`, for i.e: 
```
git checkout -b Pharmcist  
>>> You will be inside the new branch.
```

5. After you are done with your feature, push to the branch you created.

To see what you have done, you can use `git status` to see what files you have changed.
-  Red files are files that have been modified but not staged.
- Green files are files that have been staged but not committed.

To stage files for preapre to commit, use: 
- If you want to stage all files, use `git add .`
- If you want to remove a file from staging (not commiting it), use `git restore --unstage <filename>`
- If you want to add specific files, use `git add <filename>`

Check the status again to see if the files are staged. If they are, you can commit them using 

```
git status
git commit -m "Your message here"
git push origin <which_code-feature-name>
```

6. Open our repo, then create a pull request from the branch you created to the main branch to see the changes you have made. **Ask everyone to review code** before merging.
