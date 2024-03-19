删除商品流程的mermaid时序图：

mermaid

```mermaid
sequenceDiagram
    actor Client as 客户端
    participant Gateway as 网关
    participant DeleteMethod as @DeleteMapping("/delete/{id}")
    participant PreAuth as @PreAuthorize
    participant AOP as SecurityAspect
    participant Service as ProductService
    participant DB as 数据库

    Client->>Gateway: 发送删除商品请求
    Gateway->>DeleteMethod: 路由请求
    DeleteMethod->>PreAuth: 调用权限注解
    PreAuth->>AOP: 校验权限
    AOP->>Service: 请求删除商品
    Service->>DB: 删除商品记录
    DB-->>Service: 确认删除
    Service-->>AOP: 返回删除结果
    AOP-->>PreAuth: 权限和操作验证成功
    PreAuth-->>DeleteMethod: 权限确认
    DeleteMethod-->>Gateway: 返回操作结果
    Gateway-->>Client: 展示至客户端
```

修改商品流程的mermaid时序图：

mermaid

```mermaid
sequenceDiagram
    actor Client as 客户端
    participant Gateway as 网关
    participant PutMethod as @PutMapping("/update/{id}")
    participant PreAuth as @PreAuthorize
    participant AOP as SecurityAspect
    participant Service as ProductService
    participant DB as 数据库

    Client->>Gateway: 发送修改商品请求
    Gateway->>PutMethod: 路由请求
    PutMethod->>PreAuth: 调用权限注解
    PreAuth->>AOP: 校验权限
    AOP->>Service: 请求修改商品
    Service->>DB: 查询商品是否存在
    alt 商品存在
        DB-->>Service: 商品信息
        Service->>DB: 更新商品记录
        DB-->>Service: 确认更新
        Service-->>AOP: 返回修改结果
        AOP-->>PreAuth: 权限和操作验证成功
        PreAuth-->>PutMethod: 权限确认
        PutMethod-->>Gateway: 返回操作结果
    else 商品不存在
        DB-->>Service: 商品不存在信息
        Service-->>AOP: 商品不存在
        AOP->>PreAuth: 抛出异常
        PreAuth->>PutMethod: 操作失败
        PutMethod-->>Gateway: 返回错误信息
    end
    Gateway-->>Client: 展示至客户端
```

这两个时序图各自描述了删除商品和修改商品的流程。当客户端向服务器发送请求时，请求将通过网关，调用对应的控制器方法，之后执行权限注解定义的权限校验，在AOP中执行自定义的安全检查，并最终进行数据库操作。成功或失败的结果将沿相反的路径返回客户端。

商品增加接口

```mermaid
sequenceDiagram
    participant 用户
    participant 控制器 as ProductController
    participant 服务层 as ProductService
    participant DAO层 as ProductDAO
    participant 数据库 as DB
    用户->>+控制器: 发起添加商品请求 (/add)
    控制器->>+服务层: 调用addProduct(product)
    服务层->>+DAO层: save(product)
    DAO层->>+数据库: 插入商品记录
    数据库-->>-DAO层: 返回插入结果
    DAO层-->>-服务层: 返回插入结果
    服务层-->>-控制器: 返回操作状态
    控制器-->>-用户: 显示添加结果
```



商品查询接口



```mermaid
sequenceDiagram
    participant 用户
    participant 控制器 as ProductController
    participant 服务层 as ProductService
    participant DAO层 as ProductDAO
    participant 数据库 as DB
    用户->>+控制器: 发起查询商品请求 (/query)
    控制器->>+服务层: 调用queryProduct(args)
    服务层->>+DAO层: findProducts(args)
    DAO层->>+数据库: 查询商品记录
    数据库-->>-DAO层: 返回查询结果
  	DAO层-->>-服务层: 返回查询结果
	服务层-->>-控制器: 返回查询成功与分页信息
	控制器-->>-用户: 显示查询结果
```







表设计

### 商品模块 (`Product`)

**数据表结构:**

```mysql
CREATE TABLE product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  specification VARCHAR(255),
  price DECIMAL(10, 2) NOT NULL,
  stock INT DEFAULT 0,
  created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```



### 门店管理模块 (`Store`)

**数据表结构:**

```mysql
CREATE TABLE store (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  phone VARCHAR(15),
  created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```



### 台账模块 (`Accounting`)

**台账属性:** 对商品出入库行为的动态记录。 **数据表结构:**

```mysql
CREATE TABLE accounting (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id BIGINT NOT NULL,
  store_id BIGINT NOT NULL,
  type ENUM('IN', 'OUT') NOT NULL, -- 表示入库或出库
  quantity INT NOT NULL,
  transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (store_id) REFERENCES store(id)
);
```



### 消息模块 (`Message`)

**数据表结构:**

```mysql
CREATE TABLE message (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id BIGINT NOT NULL,
  out_time TIMESTAMP,
  content TEXT NOT NULL,
  read_status BOOLEAN DEFAULT FALSE,
  created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES product(id)
);
```

