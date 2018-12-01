CREATE TABLE users (
        userid serial NOT NULL,
        user_name varchar(25) NOT NULL,
        passwd varchar(25),
        status varchar(6) NOT NULL,
        CONSTRAINT pk_users_userid PRIMARY KEY (userid),
        CONSTRAINT ck_status CHECK (status IN ('parent', 'child'))
);

CREATE TABLE tasks (
        taskid serial NOT NULL,
        task_name varchar(60) NOT NULL,
        CONSTRAINT pk_task_taskid PRIMARY KEY (taskid)
);

CREATE TABLE user_day_task (
        userid integer NOT NULL,
        taskid integer NOT NULL,
        last_date_loaded date,
        completed boolean,
        note text,
        CONSTRAINT pk_user_day_task_userid_taskid PRIMARY KEY (userid, taskid),
        CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users(userid),
        CONSTRAINT fk_taskid FOREIGN KEY (taskid) REFERENCES tasks(taskid)
);

CREATE TABLE user_week_task (
        userid integer NOT NULL,
        taskid integer NOT NULL,
        last_Monday_loaded date,
        times_per_week integer,
        times_done_in_week integer,
        note text,
        CONSTRAINT pk_user_week_task_userid_taskid PRIMARY KEY (userid, taskid),
        CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users(userid),
        CONSTRAINT fk_taskid FOREIGN KEY (taskid) REFERENCES tasks(taskid)
);