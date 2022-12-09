# :file_folder: Final Project Steps

 Step1. Construct a NodeTree from user input, and save the google result to a tree
		  		  ignore the bad url
            
 Step2. 運動比賽主題明確
 
 Step3. user keyword, host keyword, Final
 
 # :link: Install Instruction #

開啟cmd(命令提示字元)輸入以下指令碼

`cd (your folder path)`

`git init`

`git remote add 本地端名稱 https://github.com/WAFFLE900/bricksdemo.git`

*`origin` 通常被預設成本地端名稱，可以改成自己喜歡的名稱


# :file_folder: Upload Files #

open your cmd(命令提示字元)

`cd (your folder path)`

`git add .` *加入所在資料夾內所有檔案
或 `git add 檔案名稱` *加入該檔案或資料夾

`git commit -m '版本名稱'` *建立新版本

`git pull --rebase 本地端名稱 master` *將檔案及資料夾從遠端拉下來本地端整合

`git push -u origin master` *將整合好的檔案及資料夾上傳到遠端

# :file_folder: Download Files #

In your computer's folder, use the command below :

`cd (your folder path)`

`git pull 本地端名稱 master`

then you can refresh the data weekly to download file from GitHub.

### If it has a conflict during the download HW

You can try

`cd (your folder path)`

`git add .`

`git commit -m "write some message"`

`git merge master`

then pull again.
