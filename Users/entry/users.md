# 用户登陆和个人主页
## 项目逻辑

此项目的重点是token，它存储在本地数据库，扮演类似浏览器中cookie的角色。

### 登陆页

- 首页获取用户的账号和密码，点击按钮向后端发送http请求。
- 登陆成功，则将后端返回的token存入本地数据库，并导航到个人页面。
- 登陆不成功，则使用ToastDialog组件显示提示信息，账号错误或密码错误。

### 个人页

个人页面高度精简，只显示用户头像。

- 导航到此页，说明用户一定已经登陆。
- 查询本地数据库，获得token。
- 向后端发送请求，获取用户头像的url。
- 将url加载到image组件。

当用户退出APP，清理后台后，仍然能记住登陆状态，导航回个人主页，不用重新登陆。
## 所含知识点
- 调用API
- 本地数据库
- 网络图片加载
- 线程
## 博客详解
[鸿蒙开发实践——用户登陆及个人主页](https://leeshy-tech.github.io/harmonyos_userlogin/)
## 注意
- 若只是参考代码，请忽略此部分。  
- 如果你想运行此项目，你需要在有公网IP的主机上拷贝运行[https://github.com/leeshy-tech/API_userLogin/blob/main/user_login_token.py](https://github.com/leeshy-tech/API_userLogin/blob/main/user_login_token.py)的后端接口，并更改MainAbilitySlice和UserInfoAbilitySlice的url。     
- 并且，后端接口里有三张网络图片的url，可能会失效。   
