-- insert member
insert into member(user_id, password, user_name, email, view_mode) values ('admin', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'administrator', 'admin@osci.kr', 'dark');
insert into member(user_id, password, user_name, email, view_mode) values ('user1', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'user1', 'user1@osci.kr', 'dark');
insert into member(user_id, password, user_name, email, view_mode) values ('user2', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'user2', 'user2@osci.kr', 'dark');
insert into member(user_id, password, user_name, email, view_mode) values ('user3', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'user3', 'user3@osci.kr', 'dark');
insert into member(user_id, password, user_name, email, view_mode) values ('user4', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'user4', 'user4@osci.kr', 'dark');
insert into member(user_id, password, user_name, email, view_mode) values ('user5', '$2a$10$R.Di1f6nOTA5FjiXQ1ZvQO8hM5OKY0fri7zIbpfSNj4EAkRgp90vW', 'user5', 'user5@osci.kr', 'dark');

-- insert role
insert into role(name) values('Administrator');
insert into role(name) values('Domain Manager');
insert into role(name) values('Domain View-Only User');

-- insert cluster
insert into cluster(name) values ('Default Cluster');
insert into cluster(name) values ('WASup Cluster');
insert into cluster(name) values ('RoRo Cluster');
insert into cluster(name) values ('Test Cluster');

-- insert domain
insert into domain(name, cluster_id) values('Default Domain', 1);
insert into domain(name, cluster_id) values('WASup Domain', 2);
insert into domain(name, cluster_id) values('RoRo Domain', 3);
insert into domain(name, cluster_id) values('Test Domain', 4);

insert into members_roles_domains(member_id, role_id, domain_id) values (1, 1, null);
insert into members_roles_domains(member_id, role_id, domain_id) values (2, 1, null);
insert into members_roles_domains(member_id, role_id, domain_id) values (3, 2, 2);
insert into members_roles_domains(member_id, role_id, domain_id) values (4, 2, 3);
insert into members_roles_domains(member_id, role_id, domain_id) values (5, 2, 4);
insert into members_roles_domains(member_id, role_id, domain_id) values (6, 3, 2);
insert into members_roles_domains(member_id, role_id, domain_id) values (6, 3, 3);
insert into members_roles_domains(member_id, role_id, domain_id) values (6, 3, 4);

-- insert host
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('OSC-Host-01', '172.31.0.17', 22, 'centos', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'OSC Host 01 - 192.168.10.136', 'Y');
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('OSC-Host-02', '172.31.0.6', 22, 'centos', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'OSC Host 02 - 192.168.10.228', 'Y');
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('OSC-Host-03', '172.31.0.7', 22, 'centos', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'OSC Host 03 - 192.168.10.232', 'Y');
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('OSC-Host-04', '172.31.0.10', 22, 'centos', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'OSC Host 04 - 192.168.10.168', 'Y');
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('OSC-Host-05', '172.31.0.12', 22, 'centos', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'OSC Host 05 - 192.168.10.175', 'Y');
insert into host(name, ip_address, port, username, password, sudoer_yn, description, monitoring_yn) values('localhost', '127.0.0.1', 22, 'root', 'bKGohcc02n99FltgXj4Dkw0s24DESAkg7FSBj7BjiWsQumRws1vIw2tHas23nTlKjJkKOCwZdbO8D5S0v1VwaWOCSRrwT9zfsbc3cu17qx2UgYJKuLbRtaKtIGGnHogBgLmpqYVt0Vgrph3N98w6F/PMbk2QoBuWOYd+EkLvTNkg8Zd56Dd3IfL8RqUX39XNBxY/XE821z1UY/rCutQhKnRXalSST+fw8KPq4MNAC/OzIfauUrj1SQlByn8qUSSDlEZFEAtXyfC6x28ZOE1HIJ2k8A/CqSFQAO3yksIANg4n6c9au0Ve8/LlD4ICZoRJ6wF/ojYcObI2zIhD2yAKJg==', 'Y', 'WASup Local System', 'Y');
insert into host_detail(host_id) values (1);
insert into host_detail(host_id) values (2);
insert into host_detail(host_id) values (3);
insert into host_detail(host_id) values (4);
insert into host_detail(host_id) values (5);
insert into host_detail(host_id) values (6);
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (1, 90, 70, 'Y', 90, 80, 'Y', 90, 70, 'Y');
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (2, 90, 70, 'Y', 90, 70, 'Y', 90, 70, 'Y');
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (3, 90, 70, 'Y', 90, 70, 'Y', 90, 70, 'Y');
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (4, 90, 70, 'Y', 90, 70, 'Y', 90, 70, 'Y');
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (5, 90, 70, 'Y', 90, 70, 'Y', 90, 70, 'Y');
insert into host_alarm(host_id, cpu_critical, cpu_warning, cpu_alarm_yn, mem_critical, mem_warning, mem_alarm_yn, disk_critical, disk_warning, disk_alarm_yn) values (6, 90, 70, 'Y', 90, 70, 'Y', 90, 70, 'Y');

-- insert engine
insert into engine(name, category, type, version, file_path, release_date) values('Playce-WASup', 'ENGINE', 'WAS', '7.0.100', '/static/repository/engines/wasup/wasup-7.0.100.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-WASup', 'ENGINE', 'WAS', '8.5.51', '/static/repository/engines/wasup/wasup-8.5.51.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-WASup', 'ENGINE', 'WAS', '9.0.31', '/static/repository/engines/wasup/wasup-9.0.31.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-Apache', 'ENGINE', 'WS', '2.4.37', '/static/repository/engines/apache/apache-2.4.37.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-Nginx', 'ENGINE', 'WS', '1.15.8', '/static/repository/engines/nginx/nginx-1.15.8.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-Dolly', 'ENGINE', 'SS', '1.0.0', '/static/repository/engines/dolly/dolly-1.0.0.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Playce-ScouterX', 'ENGINE', 'APM', '2.7.1', '/static/repository/engines/scouter/scouter-2.7.1.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Atlassian-Jira', 'ENGINE', 'JIRA', '8.5.2', '/static/repository/engines/atlassian/jira-8.5.2.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Atlassian-Confluence', 'ENGINE', 'CONFLUENCE', '7.1.2', '/static/repository/engines/atlassian/confluence-7.1.2.tar.gz', '2020-03-01 00:00:00');
insert into engine(name, category, type, version, file_path, release_date) values('Atlassian-Crowd', 'ENGINE', 'CROWD', '3.7.1', '/static/repository/engines/atlassian/crowd-3.7.1.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(1, 'Playce-WASup', 'TEMPLATE', 'WAS', '7.0', '/static/repository/templates/wasup/wasup-svr-7.0.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(2, 'Playce-WASup', 'TEMPLATE', 'WAS', '8.5', '/static/repository/templates/wasup/wasup-svr-8.5.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(3, 'Playce-WASup', 'TEMPLATE', 'WAS', '9.0', '/static/repository/templates/wasup/wasup-svr-9.0.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(4, 'Playce-Apache', 'TEMPLATE', 'WS', '2.4', '/static/repository/templates/apache/apache-svr-2.4.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(5, 'Playce-Nginx', 'TEMPLATE', 'WS', '1.15', '/static/repository/templates/nginx/nginx-svr-1.15.tar.gz', '2020-03-01 00:00:00');
insert into engine(ref_id, name, category, type, version, file_path, release_date) values(6, 'Playce-Dolly', 'TEMPLATE', 'SS', '1.0', '/static/repository/templates/dolly/dolly-svr-1.0.tar.gz', '2020-03-01 00:00:00');

-- insert hosts_engines
insert into hosts_engines(host_id, engine_id, install_path) values(1, 1, '/opt/WASup/engines/wasup-7.0');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 2, '/opt/WASup/engines/wasup-8.5');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 3, '/opt/WASup/engines/wasup-9.0');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 4, '/opt/WASup/engines/apache-2.4');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 5, '/opt/WASup/engines/nginx-1.15');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 6, '/opt/WASup/engines/dolly-1.0');
insert into hosts_engines(host_id, engine_id, install_path) values(1, 7, '/opt/WASup/scouter-2.7.0/server');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 1, '/opt/WASup/engines/wasup-7.0');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 2, '/opt/WASup/engines/wasup-8.5');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 3, '/opt/WASup/engines/wasup-9.0');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 4, '/opt/WASup/engines/apache-2.4');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 5, '/opt/WASup/engines/nginx-1.15');
insert into hosts_engines(host_id, engine_id, install_path) values(2, 6, '/opt/WASup/engines/dolly-1.0');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 1, '/opt/WASup/engines/wasup-7.0');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 2, '/opt/WASup/engines/wasup-8.5');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 3, '/opt/WASup/engines/wasup-9.0');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 4, '/opt/WASup/engines/apache-2.4');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 5, '/opt/WASup/engines/nginx-1.15');
insert into hosts_engines(host_id, engine_id, install_path) values(3, 6, '/opt/WASup/engines/dolly-1.0');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 1, '/opt/WASup/engines/wasup-7.0');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 2, '/opt/WASup/engines/wasup-8.5');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 3, '/opt/WASup/engines/wasup-9.0');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 4, '/opt/WASup/engines/apache-2.4');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 5, '/opt/WASup/engines/nginx-1.15');
insert into hosts_engines(host_id, engine_id, install_path) values(4, 6, '/opt/WASup/engines/dolly-1.0');
insert into hosts_engines(host_id, engine_id, install_path) values(5, 7, '/opt/WASup/servers/scouter');
insert into hosts_engines(host_id, engine_id, install_path) values(5, 8, '/opt/WASup/servers/jira');
insert into hosts_engines(host_id, engine_id, install_path) values(5, 9, '/opt/WASup/servers/confluence');
insert into hosts_engines(host_id, engine_id, install_path) values(5, 10, '/opt/WASup/servers/crowd');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 1, '/opt/WASup/engines/wasup-7.0');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 2, '/opt/WASup/engines/wasup-8.5');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 3, '/opt/WASup/engines/wasup-9.0');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 4, '/opt/WASup/engines/apache-2.4');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 5, '/opt/WASup/engines/nginx-1.15');
insert into hosts_engines(host_id, engine_id, install_path) values(6, 6, '/opt/WASup/engines/dolly-1.0');

-- insert web app server
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (1, 1, 1, 'wasup-app-01', 8009, 8080, 0, 8005, '/opt/WASup/servers/wasup-app-01', '/usr/java/jdk1.8', 9999, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (1, 2, 2, 'wasup-app-02', 8109, 8180, 100, 8105, '/opt/WASup/servers/wasup-app-02', '/usr/java/jdk1.8', 10099, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (1, 3, 3, 'wasup-app-03', 8209, 8280, 200, 8205, '/opt/WASup/servers/wasup-app-03', '/usr/java/jdk1.8', 10199, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (2, 1, 1, 'wasup-app-04', 8009, 8080, 0, 8005, '/opt/WASup/servers/wasup-app-04', '/usr/java/jdk1.8', 9999, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (2, 2, 2, 'wasup-app-05', 8109, 8180, 100, 8105, '/opt/WASup/servers/wasup-app-05', '/usr/java/jdk1.8', 10099, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (2, 3, 3, 'wasup-app-06', 8209, 8280, 200, 8205, '/opt/WASup/servers/wasup-app-06', '/usr/java/jdk1.8', 10199, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (3, 1, 1, 'wasup-app-07', 8009, 8080, 0, 8005, '/opt/WASup/servers/wasup-app-07', '/usr/java/jdk1.8', 9999, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (3, 2, 2, 'wasup-app-08', 8109, 8180, 100, 8105, '/opt/WASup/servers/wasup-app-08', '/usr/java/jdk1.8', 10099, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (3, 3, 3, 'wasup-app-09', 8209, 8280, 200, 8205, '/opt/WASup/servers/wasup-app-09', '/usr/java/jdk1.8', 10199, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (4, 1, 1, 'wasup-app-10', 8009, 8080, 0, 8005, '/opt/WASup/servers/wasup-app-10', '/usr/java/jdk1.8', 9999, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (4, 2, 2, 'wasup-app-11', 8109, 8180, 100, 8105, '/opt/WASup/servers/wasup-app-11', '/usr/java/jdk1.8', 10099, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (4, 3, 3, 'wasup-app-12', 8209, 8280, 200, 8205, '/opt/WASup/servers/wasup-app-12', '/usr/java/jdk1.8', 10199, 30, 'centos', 'Y', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (6, 4, 1, 'sample-was-1', 8019, 8090, 10, 8015, '/opt/WASup/servers/sample-was-1', '/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home', 10009, 30, 'wasup', 'N', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (6, 4, 2, 'sample-was-2', 8109, 8180, 100, 8105, '/opt/WASup/servers/sample-was-2', '/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home', 10099, 30, 'wasup', 'N', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into web_app_server(host_id, domain_id, engine_id, name, ajp_port, http_port, port_offset, shutdown_port, install_path, java_home, rmi_port, session_timeout, run_user, monitoring_yn, jvm_options) values (6, 4, 3, 'sample-was-3', 8209, 8280, 200, 8205, '/opt/WASup/servers/sample-was-3', '/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home', 10199, 30, 'wasup', 'N', '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');

insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (1, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (2, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (3, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (4, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (5, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (6, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (7, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (8, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (9, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (10, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (11, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (12, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (13, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (14, 100, 99, 'Y');
insert into web_app_server_alarm(web_app_server_id, heap_critical, heap_warning, heap_alarm_yn) values (15, 100, 99, 'Y');

-- insert scouter server & update
insert into scouter_server(name, host_id, engine_id, ip_address, tcp_port, udp_port, paper_embedded_yn, paper_url) values('scouter-svr-01', 1, 7, '172.31.0.17', 6100, 6100, 'Y', 'http://192.168.10.136:6180/extweb/index.html');
insert into scouter_server(name, host_id, engine_id, ip_address, tcp_port, udp_port, paper_embedded_yn, paper_url) values('scouter-svr-02', 5, 7, '172.31.0.12', 6100, 6100, 'Y', 'http://192.168.10.175:6180/extweb/index.html');
insert into scouter_server(name, host_id, engine_id, ip_address, tcp_port, udp_port, paper_embedded_yn, paper_url) values('scouter-svr-03', null, null, '172.31.0.11', 6100, 6100, 'N', null);
update host set scouter_server_id = 1 where id = 1;
update host set scouter_server_id = 1 where id = 2;
update host set scouter_server_id = 1 where id = 3;
update host set scouter_server_id = 1 where id = 4;
--update host set scouter_server_id = 1 where id = 5;
update web_app_server set scouter_server_id = 1 where id = 1;
--update web_app_server set scouter_server_id = 1 where id = 2;
--update web_app_server set scouter_server_id = 1 where id = 3;
update web_app_server set scouter_server_id = 1 where id = 4;
--update web_app_server set scouter_server_id = 1 where id = 5;
--update web_app_server set scouter_server_id = 1 where id = 6;
update web_app_server set scouter_server_id = 1 where id = 7;
--update web_app_server set scouter_server_id = 1 where id = 8;
--update web_app_server set scouter_server_id = 1 where id = 9;
update web_app_server set scouter_server_id = 1 where id = 10;
--update web_app_server set scouter_server_id = 1 where id = 11;
--update web_app_server set scouter_server_id = 1 where id = 12;


-- insert atlassian server & update
insert into atlassian_server(host_id, engine_id, scouter_server_id, type, name, description, install_path, java_home, application_home, run_user, heap_min, heap_max, server_url) values(5, 8, 1, 'JIRA', 'jira-svr-01', null, '/opt/WASup/servers/jira', '/usr', '/opt/WASup/apps/jira', 'centos', 1024, 2048, 'http://192.168.10.175:8080');
insert into atlassian_server(host_id, engine_id, scouter_server_id, type, name, description, install_path, java_home, application_home, run_user, heap_min, heap_max, server_url) values(5, 9, 1, 'CONFLUENCE', 'confluence-svr-01', null, '/opt/WASup/servers/confluence', '/usr', '/opt/WASup/apps/confluence', 'centos', 1024, 2048, 'http://192.168.10.175:8090');
insert into atlassian_server(host_id, engine_id, scouter_server_id, type, name, description, install_path, java_home, application_home, run_user, heap_min, heap_max, server_url) values(5, 10, 1, 'CROWD', 'crowd-svr-01', null, '/opt/WASup/servers/crowd', '/usr', '/opt/WASup/apps/crowd', 'centos', 128, 512, 'http://192.168.10.175:8095');

-- insert web server
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (1, 1, 4, 'wasup-web-01', 80, '/opt/WASup/servers/wasup-web-01', '/opt/WASup/servers/wasup-web-01/htdocs', 443, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (1, 2, 5, 'wasup-web-02', 81, '/opt/WASup/servers/wasup-web-02', '/opt/WASup/servers/wasup-web-02/html', 444, '/,~* \.(?:css|js)$');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (1, 3, 5, 'wasup-web-03', 82, '/opt/WASup/servers/wasup-web-03', '/opt/WASup/servers/wasup-web-03/html', 445, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (2, 1, 5, 'wasup-web-04', 80, '/opt/WASup/servers/wasup-web-04', '/opt/WASup/servers/wasup-web-04/html', 443, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (2, 2, 4, 'wasup-web-05', 81, '/opt/WASup/servers/wasup-web-05', '/opt/WASup/servers/wasup-web-05/htdocs', 444, '/diff-test,/test');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (2, 3, 4, 'wasup-web-06', 82, '/opt/WASup/servers/wasup-web-06', '/opt/WASup/servers/wasup-web-06/htdocs', 445, '/diff-test,/test');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (3, 1, 4, 'wasup-web-07', 80, '/opt/WASup/servers/wasup-web-07', '/opt/WASup/servers/wasup-web-07/htdocs', 443, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (3, 2, 5, 'wasup-web-08', 81, '/opt/WASup/servers/wasup-web-08', '/opt/WASup/servers/wasup-web-08/html', 444, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (3, 3, 4, 'wasup-web-09', 82, '/opt/WASup/servers/wasup-web-09', '/opt/WASup/servers/wasup-web-09/htdocs', 445, '~ /(WEB-INF|META-INF|org)/');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (4, 1, 4, 'wasup-web-10', 80, '/opt/WASup/servers/wasup-web-10', '/opt/WASup/servers/wasup-web-10/htdocs', 443, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (4, 2, 5, 'wasup-web-11', 81, '/opt/WASup/servers/wasup-web-11', '/opt/WASup/servers/wasup-web-11/html', 444, '/,~* \.(?:css|js)$');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (4, 3, 5, 'wasup-web-12', 82, '/opt/WASup/servers/wasup-web-12', '/opt/WASup/servers/wasup-web-12/html', 445, '~ /(WEB-INF|META-INF|org)/');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (6, 4, 4, 'sample-web-1', 9090, '/opt/WASup/servers/sample-web-1', '/opt/WASup/servers/sample-web-1/htdocs', 9443, '/*.jsp,/*.do');
insert into web_server(host_id, domain_id, engine_id, name, http_port, install_path, document_root, ssl_port, patterns) values (6, 4, 5, 'sample-web-2', 9190, '/opt/WASup/servers/sample-web-2', '/opt/WASup/servers/sample-web-2/www', 9543, '/*.jsp,/*.do');

insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (1, 1);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (2, 2);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (2, 5);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (2, 8);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (3, 3);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (3, 6);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (3, 9);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (5, 2);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (5, 5);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (5, 8);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (6, 3);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (6, 6);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (6, 9);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (8, 2);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (8, 5);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (8, 8);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (9, 3);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (9, 6);
insert into web_servers_web_app_servers(web_server_id, web_app_server_id) values (9, 9);

-- insert session server
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (1, 1, 6, 'wasup-session-01', 11222, 7800, '/opt/WASup/servers/wasup-session-01', '/usr/java/jdk1.8', 1099, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (1, 2, 6, 'wasup-session-02', 11322, 7900, '/opt/WASup/servers/wasup-session-02', '/usr/java/jdk1.8', 1199, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (1, 3, 6, 'wasup-session-03', 11422, 8000, '/opt/WASup/servers/wasup-session-03', '/usr/java/jdk1.8', 1299, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (2, 1, 6, 'wasup-session-04', 11222, 7800, '/opt/WASup/servers/wasup-session-04', '/usr/java/jdk1.8', 1099, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (2, 2, 6, 'wasup-session-05', 11322, 7900, '/opt/WASup/servers/wasup-session-05', '/usr/java/jdk1.8', 1199, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (2, 3, 6, 'wasup-session-06', 11422, 8000, '/opt/WASup/servers/wasup-session-06', '/usr/java/jdk1.8', 1299, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (3, 1, 6, 'wasup-session-07', 11222, 7800, '/opt/WASup/servers/wasup-session-07', '/usr/java/jdk1.8', 1099, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (3, 2, 6, 'wasup-session-08', 11322, 7900, '/opt/WASup/servers/wasup-session-08', '/usr/java/jdk1.8', 1199, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (3, 3, 6, 'wasup-session-09', 11422, 8000, '/opt/WASup/servers/wasup-session-09', '/usr/java/jdk1.8', 1299, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (4, 1, 6, 'wasup-session-10', 11222, 7800, '/opt/WASup/servers/wasup-session-10', '/usr/java/jdk1.8', 1099, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (4, 2, 6, 'wasup-session-11', 11322, 7900, '/opt/WASup/servers/wasup-session-11', '/usr/java/jdk1.8', 1199, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (4, 3, 6, 'wasup-session-12', 11422, 8000, '/opt/WASup/servers/wasup-session-12', '/usr/java/jdk1.8', 1299, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');
insert into session_server(host_id, cluster_id, engine_id, name, hotrod_port, jgroups_port, install_path, java_home, rmi_port, jvm_options) values (6, 4, 6, 'sample-session', 11222, 7800, '/opt/WASup/servers/sample-session', '/usr/java/jdk1.8', 1099, '-Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m');

-- insert config file
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-01/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-01/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-01/conf/context.xml', 'This is sample context.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-01/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (1, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-02/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-02/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-02/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-02/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (2, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-02/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-03/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-03/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-03/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-03/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (3, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-03/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-04/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-04/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-04/conf/context.xml', 'This is sample context.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-04/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (4, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-04/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-05/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-05/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-05/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-05/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (5, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-05/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-06/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-06/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-06/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-06/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (6, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-06/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-07/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-07/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-07/conf/context.xml', 'This is sample context.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-07/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (7, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-07/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-08/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-08/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-08/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-08/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-08/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (8, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-01/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-09/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-09/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-09/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-09/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (9, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-09/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-10/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-10/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-10/conf/context.xml', 'This is sample context.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-10/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (10, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-10/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-11/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-11/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-11/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-11/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (11, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-11/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'env.sh', '/opt/WASup/servers/wasup-app-12/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'server.xml', '/opt/WASup/servers/wasup-app-12/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'context.xml', '/opt/WASup/servers/wasup-app-12/conf/context.xml', 'This is sample context.xml');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'scouter.conf', '/opt/WASup/servers/wasup-app-12/scouter/conf/scouter.conf', 'This is sample scouter.conf');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'capture.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/capture.plug', 'This is sample capture.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'counter.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/counter.plug', 'This is sample counter.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'httpcall.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'httpservice.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'jdbcpool.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'service.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/service.plug', 'This is sample service.plug');
-- insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (12, null, null, 1, 'springControllerCapture.plug', '/opt/WASup/servers/wasup-app-12/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (13, null, null, 1, 'env.sh', '/opt/WASup/servers/sample-was-1/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (13, null, null, 1, 'server.xml', '/opt/WASup/servers/sample-was-1/conf/server.xml', 'This is sample server.xml');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (13, null, null, 1, 'context.xml', '/opt/WASup/servers/sample-was-1/conf/context.xml', 'This is sample context.xml');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 1, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-01/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 1, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-01/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 1, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-01/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 1, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-01/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 1, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-01/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 2, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-02/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 2, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-02/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 2, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-02/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 2, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-02/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 3, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-03/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 3, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-03/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 3, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-03/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 3, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-03/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 4, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-04/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 4, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-04/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 4, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-04/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 4, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-04/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 5, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-05/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 5, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-05/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 5, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-05/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 5, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-05/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 5, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-05/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 6, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-06/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 6, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-06/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 6, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-06/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 6, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-06/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 6, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-06/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 7, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-07/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 7, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-07/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 7, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-07/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 7, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-07/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 7, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-07/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 8, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-08/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 8, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-08/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 8, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-08/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 8, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-08/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 9, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-09/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 9, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-09/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 9, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-09/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 9, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-09/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 9, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-09/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 10, null, 1, 'httpd.conf', '/opt/WASup/servers/wasup-web-10/conf/httpd.conf', 'This is sample httpd.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 10, null, 1, 'httpd-ssl.conf', '/opt/WASup/servers/wasup-web-10/conf/httpd-ssl.conf', 'This is sample httpd-ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 10, null, 1, 'mod_jk.conf', '/opt/WASup/servers/wasup-web-10/conf/mod_jk.conf', 'This is sample mod_jk.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 10, null, 1, 'uriworkermap.properties', '/opt/WASup/servers/wasup-web-10/conf/uriworkermap.properties', 'This is sample uriworkermap.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 10, null, 1, 'workers.properties', '/opt/WASup/servers/wasup-web-10/conf/workers.properties', 'This is sample workers.properties');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 11, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-11/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 11, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-11/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 11, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-11/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 11, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-11/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 12, null, 1, 'nginx.conf', '/opt/WASup/servers/wasup-web-12/conf/nginx.conf', 'This is sample nginx.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 12, null, 1, 'default.conf', '/opt/WASup/servers/wasup-web-12/conf.d/default.conf', 'This is sample default.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 12, null, 1, 'ssl.conf', '/opt/WASup/servers/wasup-web-12/conf.d/ssl.conf', 'This is sample ssl.conf');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, 12, null, 1, 'upstream.conf', '/opt/WASup/servers/wasup-web-12/conf.d/upstream.conf', 'This is sample upstream.conf');

insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 1, 1, 'env.sh', '/opt/WASup/servers/wasup-session-01/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 1, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-01/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 2, 1, 'env.sh', '/opt/WASup/servers/wasup-session-02/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 2, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-02/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 3, 1, 'env.sh', '/opt/WASup/servers/wasup-session-03/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 3, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-03/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 4, 1, 'env.sh', '/opt/WASup/servers/wasup-session-04/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 4, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-04/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 5, 1, 'env.sh', '/opt/WASup/servers/wasup-session-05/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 5, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-05/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 6, 1, 'env.sh', '/opt/WASup/servers/wasup-session-06/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 6, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-06/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 7, 1, 'env.sh', '/opt/WASup/servers/wasup-session-07/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 7, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-07/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 8, 1, 'env.sh', '/opt/WASup/servers/wasup-session-08/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 8, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-08/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 9, 1, 'env.sh', '/opt/WASup/servers/wasup-session-09/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 9, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-09/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 10, 1, 'env.sh', '/opt/WASup/servers/wasup-session-10/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 10, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-10/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 11, 1, 'env.sh', '/opt/WASup/servers/wasup-session-11/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 11, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-11/conf/dolly.properties', 'This is sample dolly.properties');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 12, 1, 'env.sh', '/opt/WASup/servers/wasup-session-12/bin/env.sh', 'This is sample env.sh');
insert into config_file(web_app_server_id, web_server_id, session_server_id, version, name, file_path, contents) values (null, null, 12, 1, 'dolly.properties', '/opt/WASup/servers/wasup-session-12/conf/dolly.properties', 'This is sample dolly.properties');

insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'scouter.conf', '/opt/WASup/scouter-2.7.0/server/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'alert.plug', '/opt/WASup/scouter-2.7.0/server/plugin/alert.plug', 'This is sample alert.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'counter.plug', '/opt/WASup/scouter-2.7.0/server/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'object.plug', '/opt/WASup/scouter-2.7.0/server/plugin/object.plug', 'This is sample object.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'summary.plug', '/opt/WASup/scouter-2.7.0/server/plugin/summary.plug', 'This is sample summary.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'xlog.plug', '/opt/WASup/scouter-2.7.0/server/plugin/xlog.plug', 'This is sample xlog.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'xlogdb.plug', '/opt/WASup/scouter-2.7.0/server/plugin/xlogdb.plug', 'This is sample xlogdb.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (1, 1, 'xlogprofile.plug', '/opt/WASup/scouter-2.7.0/server/plugin/xlogprofile.plug', 'This is sample xlogprofile.plug');

insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'scouter.conf', '/opt/WASup/servers/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'alert.plug', '/opt/WASup/servers/scouter/plugin/alert.plug', 'This is sample alert.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'counter.plug', '/opt/WASup/servers/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'object.plug', '/opt/WASup/servers/scouter/plugin/object.plug', 'This is sample object.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'summary.plug', '/opt/WASup/servers/scouter/plugin/summary.plug', 'This is sample summary.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'xlog.plug', '/opt/WASup/servers/scouter/plugin/xlog.plug', 'This is sample xlog.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'xlogdb.plug', '/opt/WASup/servers/scouter/plugin/xlogdb.plug', 'This is sample xlogdb.plug');
insert into config_file(scouter_server_id, version, name, file_path, contents) values (2, 1, 'xlogprofile.plug', '/opt/WASup/servers/scouter/plugin/xlogprofile.plug', 'This is sample xlogprofile.plug');

insert into config_file(host_id, version, name, file_path, contents) values (1, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(host_id, version, name, file_path, contents) values (2, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(host_id, version, name, file_path, contents) values (3, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(host_id, version, name, file_path, contents) values (4, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(host_id, version, name, file_path, contents) values (5, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(host_id, version, name, file_path, contents) values (6, 1, 'scouter.conf', '/home/centos/wasup-agent/scouter/conf/scouter.conf', 'This is sample scouter.conf');

insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'setenv.sh', '/opt/WASup/servers/jira/bin/setenv.sh', 'This is sample setenv.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'user.sh', '/opt/WASup/servers/jira/bin/user.sh', 'This is sample user.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'server.xml', '/opt/WASup/servers/jira/conf/server.xml', 'This is sample server.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'jira-application.properties', '/opt/WASup/servers/jira/atlassian-jira/WEB-INF/classes/jira-application.properties', 'This is sample jira-application.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'seraph-config.xml', '/opt/WASup/servers/jira/atlassian-jira/WEB-INF/classes/seraph-config.xml', 'This is sample seraph-config.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'crowd.properties', '/opt/WASup/servers/jira/atlassian-jira/WEB-INF/classes/crowd.properties', 'This is sample crowd.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'dbconfig.xml', '/opt/WASup/apps/jira/dbconfig.xml', 'This is sample dbconfig.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'scouter.conf', '/opt/WASup/servers/jira/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'capture.plug', '/opt/WASup/servers/jira/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'counter.plug', '/opt/WASup/servers/jira/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'httpcall.plug', '/opt/WASup/servers/jira/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'httpservice.plug', '/opt/WASup/servers/jira/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'jdbcpool.plug', '/opt/WASup/servers/jira/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'service.plug', '/opt/WASup/servers/jira/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (1, 1, 'springControllerCapture.plug', '/opt/WASup/servers/jira/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');

insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'setenv.sh', '/opt/WASup/servers/confluence/bin/setenv.sh', 'This is sample setenv.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'user.sh', '/opt/WASup/servers/confluence/bin/user.sh', 'This is sample user.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'server.xml', '/opt/WASup/servers/confluence/conf/server.xml', 'This is sample server.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'confluence-init.properties', '/opt/WASup/servers/confluence/confluence/WEB-INF/confluence-init.properties', 'This is sample confluence-init.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'seraph-config.xml', '/opt/WASup/servers/confluence/confluence/WEB-INF/classes/seraph-config.xml', 'This is sample seraph-config.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'crowd.properties', '/opt/WASup/servers/confluence/confluence/WEB-INF/classes/crowd.properties', 'This is sample crowd.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'confluence.cfg.xml', '/opt/WASup/apps/confluence/confluence.cfg.xml', 'This is sample confluence.cfg.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'scouter.conf', '/opt/WASup/servers/confluence/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'capture.plug', '/opt/WASup/servers/confluence/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'counter.plug', '/opt/WASup/servers/confluence/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'httpcall.plug', '/opt/WASup/servers/confluence/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'httpservice.plug', '/opt/WASup/servers/confluence/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'jdbcpool.plug', '/opt/WASup/servers/confluence/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'service.plug', '/opt/WASup/servers/confluence/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (2, 1, 'springControllerCapture.plug', '/opt/WASup/servers/confluence/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');

insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'setenv.sh', '/opt/WASup/servers/crowd/apache-tomcat/bin/setenv.sh', 'This is sample setenv.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'catalina.sh', '/opt/WASup/servers/crowd/apache-tomcat/bin/catalina.sh', 'This is sample catalina.sh');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'server.xml', '/opt/WASup/servers/crowd/apache-tomcat/conf/server.xml', 'This is sample server.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'crowd-init.properties', '/opt/WASup/servers/crowd/crowd-webapp/WEB-INF/classes/crowd-init.properties', 'This is sample crowd-init.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'log4j.properties', '/opt/WASup/servers/crowd/crowd-webapp/WEB-INF/classes/log4j.properties', 'This is sample log4j.properties');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'crowd.cfg.xml', '/opt/WASup/apps/crowd/shared/crowd.cfg.xml', 'This is sample crowd.cfg.xml');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'scouter.conf', '/opt/WASup/servers/crowd/scouter/conf/scouter.conf', 'This is sample scouter.conf');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'capture.plug', '/opt/WASup/servers/crowd/scouter/plugin/capture.plug', 'This is sample capture.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'counter.plug', '/opt/WASup/servers/crowd/scouter/plugin/counter.plug', 'This is sample counter.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'httpcall.plug', '/opt/WASup/servers/crowd/scouter/plugin/httpcall.plug', 'This is sample httpcall.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'httpservice.plug', '/opt/WASup/servers/crowd/scouter/plugin/httpservice.plug', 'This is sample httpservice.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'jdbcpool.plug', '/opt/WASup/servers/crowd/scouter/plugin/jdbcpool.plug', 'This is sample jdbcpool.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'service.plug', '/opt/WASup/servers/crowd/scouter/plugin/service.plug', 'This is sample service.plug');
insert into config_file(atlassian_server_id, version, name, file_path, contents) values (3, 1, 'springControllerCapture.plug', '/opt/WASup/servers/crowd/scouter/plugin/springControllerCapture.plug', 'This is sample springControllerCapture.plug');

-- insert web app server engine
-- insert into web_app_servers_engines(web_app_server_id, engine_id, install_path) values(1, 1, '/usr/share/tomcat8');
-- insert into web_app_servers_engines(web_app_server_id, engine_id, install_path) values(2, 1, '/usr/share/backup/tomcat8');
-- insert into web_app_servers_engines(web_app_server_id, engine_id, install_path) values(3, 1, '/usr/local/tomcat8');

-- insert web server engine
-- insert into web_servers_engines(web_server_id, engine_id, install_path) values(1, 2, '/root/nginx');
-- insert into web_servers_engines(web_server_id, engine_id, install_path) values(2, 2, '/tmp/nginx/nginx');

-- insert sessio server engine
-- insert into session_servers_engines(session_server_id, engine_id, install_path) values(1, 3, '/opt/dolly');

-- insert datasource
insert into datasource(db_type, name, description, jndi_name, username, password, jdbc_url, driver_file_path, driver_class_name, validation_query) values('ORACLE', 'Oracle-DataSource', 'This datasource is for Oracle DB', 'oracleDS', 'scott', 's4M/HMnN0Ab2nKCd8fWh4/EkTBZX7iJuybK6x7v74lPc4wZO9fmDfffgecHMvWlsEFzrDswbT76ywxupp6oG+jKUNNbtY4OuBEXgmF2JnD3j3HnpkoAXGSr/fDHhSADuhMNqANnLDpdDWdfPARW7kAH9etTg5cjWCajN3RpZCZIboNrapk+p1zWdimnbpvII5d3JNPh4lOopaYrXLcsCWgK7zkb3u5T99JQgJU/nMSi75y+jc6gWQ8pgLQmqMeSL8b5Vt3PZqcbW5D4wWyndSUgO6gajPS30r9dOPIFVbSwMdZ89M0zTeHq0LVNceworsTBgrCv/nzzlIWX846e0Kw==', 'jdbc:oracle:thin:@192.168.0.155:1521:oscOra12', '/static/repository/drivers/1/ojdbc6.jar', 'oracle.jdbc.OracleDriver', 'SELECT 1 FROM DUAL');
insert into datasource(db_type, name, description, jndi_name, username, password, jdbc_url, driver_file_path, driver_class_name, validation_query) values('MYSQL', 'MySQL-DataSource', 'This datasource is for MySQL DB', 'mysqlDS', 'meerkat', 'UOjZHLgDaqB6qZqsFfN/aEaAMCj/qZRPah6GhI296G3Jr/1mG7rXdHUFWt5f5HM9PM5xpekEFv0c/sVU+PgtYYhvd/gQ43Qfs8LLXMlpCiakJBor8I3Go0KoXlRzuvIDrzbmHTbbYQnuI3toT0i3uX4/uXIBUpSK/YRXxxmDyNK3TgmUDtRhL47iE/8Z5ZQyh8TpHULgAeXrCliLLJR3ivD0+NNgARahW7YMzBdz9D9oXrvJhTl56918jj8bR9VXHFymSRpbWPTJ1COAIz6IwbYRuMDzlMKbrfUgGexv42eolvOq2MP7O4FSs+4+HzbQQOfUxQU6f3i6hDcSn9qfXQ==', 'jdbc:mysql://192.168.10.166:3306/athena_meerkat?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC', '/static/repository/drivers/2/mysql-connector-java-6.0.6.jar', 'com.mysql.cj.jdbc.Driver', 'SELECT 1');
insert into datasource(db_type, name, description, jndi_name, username, password, jdbc_url, driver_file_path, driver_class_name, validation_query) values('DERBY', 'Derby-DataSource', 'This datasource is for Derby DB', 'derbyDS', 'wasup', 'kfC3svYqP3KbuN/eaW2HIhUey1OpWUWq/LXeXhe6qh78Nau7sRvDGXkpOh3pcKnTHhqELIEPqHOuofqgPObfmbeb6UDhGHc22Re3JBwL66hKfDOfpw7yu7no/4psRJiLrDLNUtTufHE5wzFvVEvuZ2Kn0RXBdTkYklEKoyPJGHHmNfTVExizLLGq95n8yJmiQVZczV5JC96snTaNz3LFVcl/VE8D8BX7xQD+UmN7Xyy7Ipfzdfh4hyIU2lxkb5RYn6QNyE9ks6PFnUndcsMy621DePsZ60R3f2C4DniskuQT0i6sQprhDzlaqKS0Us4nI1djliviCsUDnu5uw3TFiw==', 'jdbc:derby://localhost:1527/wasupDB', '/static/repository/drivers/3/derbyclient-10.14.2.0.jar', 'org.apache.derby.jdbc.ClientDriver', 'VALUES 1');

-- insert web app server datasource
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(2, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(2, 2);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(3, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(3, 2);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(5, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(5, 2);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(6, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(6, 2);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(8, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(8, 2);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(9, 1);
insert into web_app_servers_datasources(web_app_server_id, datasource_id) values(9, 2);

-- insert application
insert into application(name, description, context, version, file_path, doc_base) values('WASup Web Application', 'WASup Web Application', '/wasup', '1.0', '/static/repository/applications/1/wasup.war', '/opt/WASup/apps/wasup');
insert into application(name, description, context, version, file_path, doc_base) values('RoRo Web Application', 'RoRo Web Application', '/roro', '1.0', '/static/repository/applications/2/roro.war', '/opt/WASup/apps/roro');
insert into application(name, description, context, version, file_path, doc_base) values('Jenkins Web Application', 'Jenkins Web Application', '/jenkins', '2.0', '/static/repository/applications/3/jenkins.war', '/opt/WASup/apps/jenkins');
--insert into application(name, description, context, version, file_path, doc_base) values('Sample Web Application', 'Sample Web Application', '/sample', '1.0', '/static/repository/applications/4/sample.war', '/opt/WASup/apps/sample');

-- insert web app server application
insert into web_app_servers_applications(web_app_server_id, application_id) values(2, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(2, 2);
insert into web_app_servers_applications(web_app_server_id, application_id) values(3, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(3, 2);
insert into web_app_servers_applications(web_app_server_id, application_id) values(5, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(5, 2);
insert into web_app_servers_applications(web_app_server_id, application_id) values(6, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(6, 2);
insert into web_app_servers_applications(web_app_server_id, application_id) values(8, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(8, 2);
insert into web_app_servers_applications(web_app_server_id, application_id) values(9, 1);
insert into web_app_servers_applications(web_app_server_id, application_id) values(9, 2);

--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, 1, null, null, 75, 'Application(1) deploy to application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, 2, null, null, 75, 'Application(2) deploy to application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, 3, null, null, 75, 'Application(3) deploy to application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, 4, null, null, 75, 'Application(4) deploy to application server(1)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, 1, null, null, 76, 'Application(1) undeploy from application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, 2, null, null, 76, 'Application(2) undeploy from application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, 3, null, null, 76, 'Application(3) undeploy from application server(1)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, 4, null, null, 76, 'Application(4) undeploy from application server(1)', 'FAILED', '12345');

--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, null, 1, null, 65, 'Datasource(1) deploy to application server(1)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, null, 2, null, 65, 'Datasource(2) deploy to application server(1)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, null, 3, null, 65, 'Datasource(3) deploy to application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, null, 1, null, 66, 'Datasource(1) undeploy from application server(1)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, null, 2, null, 66, 'Datasource(2) undeploy from application server(1)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (1, null, null, null, 3, null, 66, 'Datasource(3) undeploy from application server(1)', 'SUCCESS', '12345');

--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (5, null, null, null, null, 1, 35, 'Set session clustering config to application server(5)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (6, null, null, null, null, 1, 35, 'Set session clustering config to application server(6)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (7, null, null, null, null, 1, 35, 'Set session clustering config to application server(7)', 'FAILED', '12345');

--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (null, 3, null, null, null, null, 45, 'Set load balancing config to web server(3)', 'FAILED', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (null, 4, null, null, null, null, 45, 'Set load balancing config to web server(4)', 'SUCCESS', '12345');

--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (null, null, 4, null, null, 1, 55, 'Set session clustering config to session server(4)', 'SUCCESS', '12345');
--insert into history(WEB_APP_SERVER_ID, WEB_SERVER_ID, SESSION_SERVER_ID, APPLICATION_ID, DATASOURCE_ID, CLUSTER_ID, CODE, TITLE, STATUS_CODE, PROCESS_UUID) values (null, null, 4, null, null, 1, 55, 'Set session clustering config to session server(4)', 'FAILED', '12345');
