import jenkins.model.Jenkins
import hudson.security.HudsonPrivateSecurityRealm
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy

def jenkins = Jenkins.getInstance()

// 从环境变量获取管理员用户名和密码
def adminUser = System.getenv("JENKINS_ADMIN_USER") ?: "admin"
def adminPassword = System.getenv("JENKINS_ADMIN_PASSWORD") ?: "admin123"

// 配置安全领域为本地用户数据库
if (!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm)) {
    println("配置 Jenkins 安全领域...")
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false, false))
}

// 获取安全领域
def realm = jenkins.getSecurityRealm()

// 检查 admin 用户是否存在
if (realm.getUser(adminUser) == null) {
    println("创建管理员用户: ${adminUser}")
    def user = realm.createAccount(adminUser, adminPassword)
    user.setFullName("Jenkins Administrator")
    user.save()
    println("管理员用户已创建")
} else {
    println("管理员用户已存在")
}

// 配置授权策略
if (jenkins.getAuthorizationStrategy().getClass().simpleName == "Unsecured") {
    println("配置授权策略...")
    jenkins.setAuthorizationStrategy(new FullControlOnceLoggedInAuthorizationStrategy())
}

// 保存配置
jenkins.save()
println("Jenkins 初始化完成")
println("   用户名: ${adminUser}")
println("   密码: ${adminPassword}")