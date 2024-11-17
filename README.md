# SC2002
Developing a Hospital Management System (HMS) with java

# Instructions to start
HMS:
When logging in, you will be prompted on whether you wish to refresh the data from the csv. If you would like to test the saving feature, type in 0, otherwise, if you which to start all over, type in 1. 

All users:
When you first login, the default password will be "default". The username would be the specific id that all roles have. This is under the assumption that the user already know their id. In real application, this would have been the NRIC. Next, you would have to change password the first time you login. 

Doctor:
When first initialised, doctors will have no appointment slots. You would be required to add appointment slots where the doctor would be available for the patient to see those slots. 

# Contributions
Lee Yi Yuan - Leader, UML, Appointment System, Patient, Doctor, Medicine, Pharmacist, Administrator, Code Clean-up
Wei Jie - Administrator
Alden Budiman - Doctor
Alan - Pharmacist, UML 
Wong Jing Han - Patient, Report, Javadocs

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
