# tp-pod
IMBD query analysis

# Como preparar el entorno a partir del codigo fuente para ejecutar la aplicacion en un ambiente con varios nodos
1_ Correr el archivo ./start-node.sh en todas las computadoras que actuarán como nodos del cluster.
2_ 

# Corridas
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
    
    # 4 Nodos:
    
    # 5 Nodos:
    
    # 6 Nodos:
    
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
    
    # 4 Nodos:
    
    # 5 Nodos:
    
    # 6 Nodos:
    
    # 7 Nodos:
    
