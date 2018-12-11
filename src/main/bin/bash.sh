#!/bin/bash

source /etc/profile
PROG_NAME=$0
ACTION=$1

usage() {
    echo "Usage: $PROG_NAME {start|stop|restart|status|tailf}"
    exit 1;
}

# colors
red='\e[0;31m'
green='\e[0;32m'
yellow='\e[0;33m'
reset='\e[0m'

echoRed() { echo -e "${red}$1${reset}"; }
echoGreen() { echo -e "${green}$1${reset}"; }
echoYellow() { echo -e "${yellow}$1${reset}"; }

APP_HOME=$(cd $(dirname $0)/..; pwd)
app=${project.build.finalName}.${project.packaging}
cd $APP_HOME
mkdir -p logs

pidfile=logs/app.pid
logfile=logs/start.`date +%F`.log
JAVA_OPTS="${java_opts}"

bakdir=/data/ops/package/app_bak/${project.build.finalName}
bakfile=$bakdir/${project.build.finalName}`date +%F`.${project.packaging}

function check_pid() {
    if [ -f $pidfile ];then
        pid=`cat $pidfile`
        if [ -n $pid ]; then
            running=`ps -p $pid|grep -v "PID TTY" |wc -l`
            return $running
        fi
    fi
    return 0
}

function start() {
    check_pid
    running=$?
    if [ $running -gt 0 ];then
        echoGreen "$app now is running already, pid=`cat $pidfile`"
        return 1
    fi

        nohup java -jar $JAVA_OPTS $app >> ${logfile} 2>&1 & pid=$!
  
    echoGreen "$app starting "
    for e in $(seq 10); do
        echo -n " $e"
        sleep 1
    done
    echo $pid > $pidfile
    check_pid
    running=$?
    if [ $running -gt 0 ];then
        echoGreen " ,pid=`cat $pidfile`"
        return 1
    else
        echoRed ",started fail!!!"
    fi
}

function stop() {
    pid=`cat $pidfile`
    kill -9 $pid
    echoRed "$app stoped..."
}

function restart() {
    stop
    sleep 1
    start
}

function backup(){

  if [ ! -x $bakdir ];then
    mkdir -p $bakdir

  fi

  if [ ! -f $bakfile ];then
        cp $app $bakfile
        echo $bakfile backup finish
  else
        echo $bakfile is already backup

  fi
}

function rollback(){

  if [ ! -f $bakfile ];then
        echo $bakfile backup not found
  else
        rm -f $app
        cp $bakfile $app
        echo $app rollback finish

  fi

}

function tailf() {
        tail -f $APP_HOME/$logfile
}

function status() {
    check_pid
    running=$?
    if [ $running -gt 0 ];then
        echoGreen "$app now is running, pid=`cat $pidfile`"
    else
        echoYellow "$app is stoped"
    fi
}

function main {
   RETVAL=0
   case "$1" in
      start)
         start
         ;;
      stop)
         stop
         ;;
      restart)
         restart
         ;;
      tailf)
         tailf
         ;;
      status)
         status
         ;;
      backup)
         backup
         ;;
      rollback)
         rollback
         ;;
      *)
         usage
         ;;
      esac
   exit $RETVAL
}

main $1
