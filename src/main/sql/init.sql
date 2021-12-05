create table ShareData(dataid varchar(30),content text,creator varchar(20),create_time Timestamp);
create index idx_ShareData on ShareData(creator,create_time desc );
create table userinfo(username varchar(20),password varchar(20));
insert into userinfo values('admin','admin');
create table CMITEM                                                                                                                  
(                                                                                                                        
  id          varchar(50),                                                                                               
  createtime  DATE,                                                                                                      
  modifytime  DATE,                                                                                                      
  recordstate INTEGER,                                                                                                   
  system      varchar(50),                                                                                               
  application varchar(50),                                                                                               
  namespace   varchar(254),                                                                                              
  name        varchar(254),                                                                                              
  itemtype    varchar(50),                                                                                               
  comments    varchar(254),                                                                                              
  itemvalue   text                                                                                                       
)                                                                                                                        
;                                                                                                                        
                                                                                                                         
create table SYS_MENUS_INFO                                                                                              
(                                                                                                                        
  menu_uuid        varchar(32),                                                                                          
  menu_name        varchar(100),                                                                                         
  sys_code         varchar(64),                                                                                          
  parent_menu_uuid varchar(32),                                                                                          
  sort_num         NUMERIC,                                                                                              
  state            varchar(20),                                                                                          
  page_id          varchar(20),                                                                                          
  menu_desc        varchar(200),                                                                                         
  icon_name        varchar(100),                                                                                         
  jump_type        varchar(5),                                                                                           
  def_open_flag    NUMERIC,                                                                                              
  open_type        NUMERIC,                                                                                              
  staff_id         NUMERIC,                                                                                              
  crt_date         timestamp,                                                                                            
  upd_staff_id     NUMERIC,                                                                                              
  upd_date         timestamp,                                                                                            
  bitmap_index     INTEGER                                                                                               
)                                                                                                                        
;                                                                                                                        
create table SYS_PAGE_INFO                                                                                               
(                                                                                                                        
  page_id      varchar(32),                                                                                              
  page_code    varchar(100),                                                                                             
  page_name    varchar(50),                                                                                              
  sys_code     varchar(64),                                                                                              
  page_url     varchar(500),                                                                                             
  fixed_param  varchar(100),                                                                                             
  page_desc    varchar(500),                                                                                             
  upd_date     timestamp,                                                                                                
  upd_staff_id NUMERIC,                                                                                                  
  dim_type     varchar(50),                                                                                              
  encrypt_flag NUMERIC,                                                                                                  
  state        varchar(10),                                                                                              
  dim_code     varchar(50),                                                                                              
  staff_id     NUMERIC,                                                                                                  
  crt_date     timestamp,                                                                                                
  secret_key   varchar(32)                                                                                               
)                                                                                                                        
;                                                                                                                        
create table SYS_PAGE_MODULE_INFO                                                                                        
(                                                                                                                        
  module_id       varchar(32),                                                                                           
  module_name     varchar(100),                                                                                          
  module_code     varchar(50),                                                                                           
  view_name       varchar(50),                                                                                           
  sys_code        varchar(20),                                                                                           
  state           varchar(10),                                                                                           
  crt_date        timestamp,                                                                                             
  staff_id        NUMERIC,                                                                                               
  module_type     varchar(100),                                                                                          
  module_page_url varchar(100),                                                                                          
  upd_staff_id    NUMERIC,                                                                                               
  upd_date        timestamp,                                                                                             
  bitmap_index    INTEGER,                                                                                               
  sort            INTEGER,                                                                                               
  unit            varchar(20)                                                                                            
)                                                                                                                        
;                                                                                                                        
create table SYS_PAGE_RESOURCE_INFO                                                                                      
(                                                                                                                        
  resource_id       varchar(32),                                                                                         
  resource_name     varchar(20),                                                                                         
  resource_code     varchar(50),                                                                                         
  resource_type_id  varchar(20),                                                                                         
  state             varchar(20),                                                                                         
  crt_date          timestamp,                                                                                           
  sys_code          varchar(64),                                                                                         
  staff_id          NUMERIC,                                                                                             
  upd_staff_id      NUMERIC,                                                                                             
  upd_date          timestamp,                                                                                           
  client_type       varchar(2),                                                                                          
  icon              varchar(200),                                                                                        
  jump_url          varchar(200),                                                                                        
  bitmap_index      INTEGER,                                                                                             
  resource_property varchar(50),                                                                                         
  relate_code       varchar(200)                                                                                         
)                                                                                                                        
;                                                                                                                        
create table SYS_ROLE_INFO                                                                                               
(                                                                                                                        
  role_id          varchar(32),                                                                                          
  role_name        varchar(100),                                                                                         
  state            varchar(10),                                                                                          
  role_code        varchar(50),                                                                                          
  parent_role_id   varchar(32),                                                                                          
  role_desc        varchar(200),                                                                                         
  crt_date         timestamp,                                                                                            
  staff_id         NUMERIC,                                                                                              
  upd_date         timestamp,                                                                                            
  upd_staff_id     NUMERIC,                                                                                              
  latn_id          NUMERIC,                                                                                              
  center_role_flag NUMERIC,                                                                                              
  bitmap_index     INTEGER not null                                                                                      
)                                                                                                                        
;                                                                                                                        
create table SYS_SYSTEM_INFO                                                                                             
(                                                                                                                        
  sys_id               varchar(50),                                                                                      
  sys_code             varchar(50),                                                                                      
  sys_name             varchar(100),                                                                                     
  sys_key              varchar(20),                                                                                      
  sys_encryption_type  varchar(100),                                                                                     
  sys_desc             varchar(200),                                                                                     
  sys_state            varchar(10),                                                                                      
  sys_general_jump_url varchar(100),                                                                                     
  staff_id             NUMERIC,                                                                                          
  crt_date             timestamp,                                                                                        
  upd_staff_id         NUMERIC,                                                                                          
  upd_date             timestamp                                                                                         
)                                                                                                                        
;                                                                                                                        
create table SYS_USERS_DATAAREA_INFO                                                                                     
(                                                                                                                        
  id             NUMERIC,                                                                                                
  staff_id       NUMERIC,                                                                                                
  inter_org_id   NUMERIC,                                                                                                
  org_type       NUMERIC,                                                                                                
  child_org_flag NUMERIC,                                                                                                
  crt_date       timestamp,                                                                                              
  upd_staff_id   NUMERIC,                                                                                                
  sys_code       varchar(20),                                                                                            
  upd_date       timestamp,                                                                                              
  state          varchar(20),                                                                                            
  latn_id        NUMERIC                                                                                                 
)                                                                                                                        
;                                                                                                                        
create table SYS_USERS_EXTINFO                                                                                           
(                                                                                                                        
  id          varchar(50),                                                                                               
  empee_acct  varchar(50),                                                                                               
  ext_type    varchar(50),                                                                                               
  ext_value   varchar(200),                                                                                              
  createtime  DATE,                                                                                                      
  modifytime  DATE,                                                                                                      
  recordstate INTEGER,                                                                                                   
  creator     varchar(50)                                                                                                
)                                                                                                                        
;                                                                                                                        
create table SYS_USERS_INFO                                                                                              
(                                                                                                                        
  latn_id           NUMERIC(10),                                                                                         
  empee_acct        varchar(20),                                                                                         
  staff_nbr         varchar(50),                                                                                         
  staff_id          INTEGER not null,                                                                                    
  empee_uuid        varchar(50) not null,                                                                                
  hr_acct           varchar(30),                                                                                         
  tel_phone         varchar(20),                                                                                         
  email             varchar(50),                                                                                         
  staff_kind        varchar(30),                                                                                         
  uom_org_id        NUMERIC,                                                                                             
  staff_name        varchar(100),                                                                                        
  uom_org_name      varchar(200),                                                                                        
  org_full_name     varchar(500),                                                                                        
  empee_id          NUMERIC,                                                                                             
  crt_date          timestamp,                                                                                           
  empee_state       varchar(20),                                                                                         
  login_state       varchar(20),                                                                                         
  birthday          NUMERIC,                                                                                             
  gender            varchar(5),                                                                                          
  birth_fields      varchar(200),                                                                                        
  place_origin      varchar(100),                                                                                        
  nation            varchar(20),                                                                                         
  office_phone      varchar(20),                                                                                         
  education         varchar(50),                                                                                         
  degree            varchar(50),                                                                                         
  post              varchar(64),                                                                                         
  post_layer        varchar(50),                                                                                         
  marry_state       varchar(5),                                                                                          
  home_address      varchar(500),                                                                                        
  labor_nature      varchar(64),                                                                                         
  id_NUMERIC         varchar(50),                                                                                        
  positive_time     DATE,                                                                                                
  create_time       DATE,                                                                                                
  create_id         varchar(20),                                                                                         
  create_user       varchar(20),                                                                                         
  party_time        DATE,                                                                                                
  work_time         DATE,                                                                                                
  politics_face     varchar(64),                                                                                         
  party_state       NUMERIC,                                                                                             
  empee_type        NUMERIC,                                                                                             
  login_name        varchar(20),                                                                                         
  show_order        NUMERIC,                                                                                             
  per_promise       varchar(3000),                                                                                       
  party_post        varchar(200),                                                                                        
  is_lose_contact   NUMERIC,                                                                                             
  is_flow_party     NUMERIC,                                                                                             
  ct_position_id    NUMERIC,                                                                                             
  position_name     varchar(300),                                                                                        
  is_intra_industry NUMERIC default 0,                                                                                   
  party_per_state   NUMERIC,                                                                                             
  update_time       DATE,                                                                                                
  update_id         varchar(20),                                                                                         
  UNICODE         varchar(50),                                                                                           
  update_user       varchar(20)                                                                                          
)                                                                                                                        
;                                                                                                                        
                                                                                                                         
create table SYS_USER_ROLE_INFO                                                                                          
(                                                                                                                        
  uuid         VARCHAR(32),                                                                                              
  staff_id     NUMERIC,                                                                                                  
  role_id      VARCHAR(20),                                                                                              
  crt_date     TIMESTAMP,                                                                                                
  opr_staff_id NUMERIC,                                                                                                  
  main_flag    NUMERIC,                                                                                                  
  state        VARCHAR(20),                                                                                              
  upd_staff_id NUMERIC,                                                                                                  
  upd_date     TIMESTAMP,                                                                                                
  down_flag    NUMERIC                                                                                                   
)                                                                                                                        
;                                                                                                                        
                                                                                                                         
create table SYS_ROLE_RESOURCE_REL                                                                                       
(                                                                                                                        
  uuid         VARCHAR(32),                                                                                              
  role_id      VARCHAR(50),                                                                                              
  source_id    VARCHAR(50),                                                                                              
  crt_date     TIMESTAMP,                                                                                                
  staff_id     NUMERIC,                                                                                                  
  state        VARCHAR(10),                                                                                              
  upd_staff_id NUMERIC,                                                                                                  
  upd_date     TIMESTAMP                                                                                                 
)                                                                                                                        
;                                                                                                                        
                                                                                                                         
create table SYS_ROLE_MENU_REL                                                                                           
(                                                                                                                        
  uuid         varchar(32),                                                                                              
  role_id      varchar(32),                                                                                              
  menu_id      varchar(32),                                                                                              
  crt_date     TIMESTAMP,                                                                                                
  staff_id     NUMERIC,                                                                                                  
  state        varchar(10),                                                                                              
  upd_date     TIMESTAMP,                                                                                                
  upd_staff_id NUMERIC                                                                                                   
)                                                                                                                        
;                                                                                                                        
                                                                                                                         
create table SYS_ROLE_MADULE_REL                                                                                         
(                                                                                                                        
  uuid         varchar(32),                                                                                              
  role_id      varchar(50),                                                                                              
  madule_id    varchar(32),                                                                                              
  crt_date     TIMESTAMP,                                                                                                
  staff_id     NUMERIC,                                                                                                  
  state        varchar(10),                                                                                              
  upd_date     TIMESTAMP,                                                                                                
  upd_staff_id NUMERIC                                                                                                   
)                                                                                                                        
;