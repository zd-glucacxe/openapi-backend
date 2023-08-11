-- 切换库
use openapi;

-- 接口信息
create table if not exists openapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0  not null comment '接口状态（0-关闭 1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人 ',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('王荣轩', '范峻熙', 'www.oralee-schmitt.net', '刘哲瀚', '孙立果', 0, '夏烨霖', 48691);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郝煜城', '侯钰轩', 'www.bo-watsica.biz', '萧建辉', '王嘉懿', 0, '魏凯瑞', 950003740);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('赵越彬', '段明', 'www.dirk-rippin.org', '毛绍齐', '石博超', 0, '孔嘉熙', 9701497301);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('韦胤祥', '马瑾瑜', 'www.lawanda-zboncak.com', '曹鸿煊', '董浩', 0, '洪熠彤', 6082701618);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('周展鹏', '蔡智渊', 'www.candance-king.io', '郑乐驹', '黄熠彤', 0, '冯昊焱', 28);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('马鑫磊', '刘懿轩', 'www.valentine-paucek.name', '邹凯瑞', '毛煜祺', 0, '赖健雄', 29576023);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郝昊焱', '卢鑫磊', 'www.seymour-oberbrunner.name', '杜志泽', '陈弘文', 0, '廖烨伟', 69331);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙擎苍', '邱果', 'www.donnell-schmitt.info', '谢智宸', '萧立果', 0, '唐越泽', 176);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黄荣轩', '薛弘文', 'www.dee-berge.info', '邱明', '彭鸿涛', 0, '傅黎昕', 6);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('顾建辉', '蔡昊强', 'www.marylee-adams.co', '韩浩宇', '胡文轩', 0, '毛浩然', 2);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('毛博超', '刘烨华', 'www.sammy-ullrich.name', '夏志强', '王航', 0, '孔旭尧', 400189628);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('程立辉', '潘耀杰', 'www.adelaide-mckenzie.biz', '邓健雄', '阎耀杰', 0, '傅鹭洋', 845);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('崔熠彤', '卢智渊', 'www.deonna-keebler.name', '潘鹏涛', '蔡浩轩', 0, '赵琪', 8074720);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('贺嘉懿', '吴熠彤', 'www.son-streich.co', '苏航', '方烨霖', 0, '任子默', 6566);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('刘嘉熙', '余涛', 'www.bryant-schoen.info', '侯熠彤', '杨彬', 0, '贺子轩', 663);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('邵天磊', '陈明杰', 'www.liliana-nitzsche.com', '邵炎彬', '郭越泽', 0, '武风华', 7990343);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('覃子骞', '罗潇然', 'www.dawne-hoeger.name', '顾黎昕', '黎振家', 0, '卢越彬', 28329);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陶鸿涛', '魏靖琪', 'www.donya-schuster.co', '孔浩', '唐泽洋', 0, '罗擎宇', 56032036);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐文', '曾楷瑞', 'www.nora-gleichner.co', '何雨泽', '梁子轩', 0, '姚航', 2298710241);
insert into openapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('万昊强', '陶明', 'www.shelli-witting.io', '赵擎苍', '林潇然', 0, '顾健雄', 30889218);


-- 用户调用接口关系表
create table if not exists openapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户id ',
    `interfaceInfo` bigint not null comment '接口id ',
    `totalNum` int default 0  not null comment '总调用次数',
    `leftNum` int default 0  not null comment '剩余调用次数',
    `status` int default 0  not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

