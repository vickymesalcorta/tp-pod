# tp-pod
IMBD query analysis

## Como preparar el entorno a partir del codigo fuente para ejecutar la aplicacion en un ambiente con varios nodos
1. Correr el archivo ./start-node.sh en todas las computadoras que actuarán como nodos del cluster.
2. Para ejecutar consultas al cluster: java ar.edu.itba.pod.App [N=10|tope=1995] path=./src/main/resources/imdb-20K.json

## Corridas
  # Query 1 con el archivo imdb-20K.json: query=1 N=10 path=./src/main/resources/imdb-20K.json
    
    # 2 Nodos:
      File parsing start: 16/11/2015  04:25:25:254
      File parsing end: 16/11/2015  04:25:47:145
      Map-reduce job start: 16/11/2015  04:25:47:161
      Map-reduce job end: 16/11/2015  04:25:55:722
      
    # 3 Nodos:
      File parsing start: 16/11/2015  04:47:34:538
      File parsing end: 16/11/2015  04:47:59:293
      Map-reduce job start: 16/11/2015  04:47:59:304
      Map-reduce job end: 16/11/2015  04:48:05:237

    # 4 Nodos:
      File parsing start: 16/11/2015  04:49:55:020
      File parsing end: 16/11/2015  04:50:25:217
      Map-reduce job start: 16/11/2015  04:50:25:243
      Map-reduce job end: 16/11/2015  04:50:29:766
    
    # 5 Nodos:
      File parsing start: 16/11/2015  04:55:55:060
      File parsing end: 16/11/2015  04:56:25:534
      Map-reduce job start: 16/11/2015  04:56:25:544
      Map-reduce job end: 16/11/2015  04:56:28:597

    # 6 Nodos:
      File parsing start: 16/11/2015  04:58:38:611
      File parsing end: 16/11/2015  04:59:10:745
      Map-reduce job start: 16/11/2015  04:59:10:761
      Map-reduce job end: 16/11/2015  04:59:13:295
    
    # 7 Nodos:
      File parsing start: 16/11/2015  05:01:39:215
      File parsing end: 16/11/2015  05:02:11:834
      Map-reduce job start: 16/11/2015  05:02:11:840
      Map-reduce job end: 16/11/2015  05:02:14:251
    
  # Query 2: con el archivo imdb-20K.json: query=2 tope=1995 path=src/main/resources/imdb-20K.json
    
    # 2 Nodos:
      File parsing start: 16/11/2015  04:28:43:988
      File parsing end: 16/11/2015  04:29:03:997
      Map-reduce job start: 16/11/2015  04:29:04:003
      Map-reduce job end: 16/11/2015  04:29:04:474
    
    # 3 Nodos:
      File parsing start: 16/11/2015  05:11:27:665
      File parsing end: 16/11/2015  05:11:50:076
      Map-reduce job start: 16/11/2015  05:11:50:083
      Map-reduce job end: 16/11/2015  05:11:50:624

    # 4 Nodos:
      File parsing start: 16/11/2015  05:14:10:652
      File parsing end: 16/11/2015  05:14:36:330
      Map-reduce job start: 16/11/2015  05:14:36:335
      Map-reduce job end: 16/11/2015  05:14:36:777

    # 5 Nodos:
      File parsing start: 16/11/2015  05:19:38:964
      File parsing end: 16/11/2015  05:20:06:601
      Map-reduce job start: 16/11/2015  05:20:06:615
      Map-reduce job end: 16/11/2015  05:20:07:014
    
    # 6 Nodos:
      File parsing start: 16/11/2015  05:22:42:300
      File parsing end: 16/11/2015  05:23:13:602
      Map-reduce job start: 16/11/2015  05:23:13:613
      Map-reduce job end: 16/11/2015  05:23:14:035

    # 7 Nodos:
      File parsing start: 16/11/2015  05:31:47:485
      File parsing end: 16/11/2015  05:32:30:361
      Map-reduce job start: 16/11/2015  05:32:30:367
      Map-reduce job end: 16/11/2015  05:32:30:847
    

  # Query 3 con el archivo imdb-20K.json: query=3 path=src/main/resources/imdb-20K.json
    
    # 2 Nodos:
      File parsing start: 16/11/2015  04:30:28:321
      File parsing end: 16/11/2015  04:30:53:070
      Map-reduce job start: 16/11/2015  04:30:53:076
      Map-reduce job end: 16/11/2015  04:31:49:502
      
    # 3 Nodos:
      File parsing start: 16/11/2015  05:49:47:740
      File parsing end: 16/11/2015  05:50:13:394
      Map-reduce job start: 16/11/2015  05:50:13:403
      Map-reduce job end: 16/11/2015  05:50:59:164
    
    # 4 Nodos:
      File parsing start: 16/11/2015  05:45:16:611
      File parsing end: 16/11/2015  05:45:44:738
      Map-reduce job start: 16/11/2015  05:45:44:747
      Map-reduce job end: 16/11/2015  05:46:11:290

    # 5 Nodos:
      File parsing start: 16/11/2015  05:40:29:630
      File parsing end: 16/11/2015  05:40:57:856
      Map-reduce job start: 16/11/2015  05:40:57:862
      Map-reduce job end: 16/11/2015  05:41:20:722

    # 6 Nodos:
      File parsing start: 16/11/2015  05:38:45:965
      File parsing end: 16/11/2015  05:39:15:190
      Map-reduce job start: 16/11/2015  05:39:15:196
      Map-reduce job end: 16/11/2015  05:39:34:076

    # 7 Nodos:
      File parsing start: 16/11/2015  05:33:43:794
      File parsing end: 16/11/2015  05:34:14:404
      Map-reduce job start: 16/11/2015  05:34:14:413
      Map-reduce job end: 16/11/2015  05:34:35:995

  # Query 4 con el archivo imdb-20K.json: query=4 path=src/main/resources/imdb-20K.json
    
    # 2 Nodos:
      File parsing start: 16/11/2015  04:36:20:187
      File parsing end: 16/11/2015  04:36:39:691
      Map-reduce job start: 16/11/2015  04:36:39:696
      Map-reduce job end: 16/11/2015  04:36:42:954
      
    # 3 Nodos:
      File parsing start: 16/11/2015  05:48:35:215
      File parsing end: 16/11/2015  05:49:00:345
      Map-reduce job start: 16/11/2015  05:49:00:350
      Map-reduce job end: 16/11/2015  05:49:02:935

    # 4 Nodos:
      File parsing start: 16/11/2015  05:43:52:194
      File parsing end: 16/11/2015  05:44:18:674
      Map-reduce job start: 16/11/2015  05:44:18:679
      Map-reduce job end: 16/11/2015  05:44:20:746

    # 5 Nodos:
      File parsing start: 16/11/2015  05:42:14:268
      File parsing end: 16/11/2015  05:42:41:234
      Map-reduce job start: 16/11/2015  05:42:41:240
      Map-reduce job end: 16/11/2015  05:42:43:165

    # 6 Nodos:
      File parsing start: 16/11/2015  05:37:22:458
      File parsing end: 16/11/2015  05:37:51:703
      Map-reduce job start: 16/11/2015  05:37:51:710
      Map-reduce job end: 16/11/2015  05:37:53:322
      
    # 7 Nodos:
      File parsing start: 16/11/2015  05:35:35:886
      File parsing end: 16/11/2015  05:36:09:552
      Map-reduce job start: 16/11/2015  05:36:09:560
      Map-reduce job end: 16/11/2015  05:36:11:385
