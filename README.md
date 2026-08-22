# Jenkins LazyCat App

Jenkins 持续集成/持续交付服务器，懒猫桌面应用版。

## 功能

- Jenkins 主服务（Web UI + 构建执行）
- SSH 构建代理支持
- 管理员账号自动初始化
- 免密登录自动填充

## 使用

1. 部署时配置管理员用户名和密码
2. 可选配置 SSH 代理公钥
3. 从应用入口打开 Jenkins Web 界面

## 构建

```bash
lzc-cli project release -o app.lpk
```

## 许可

MIT License