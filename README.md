# UETnlp
UETnlp is an open-source toolkit written in Java and developed with Esclipse IDE. This toolkit supports 4 steps for Vietnamese language processing in a pepline: tokenization, sentence segmentation, word segmentation, part-of-speech tagging. The word segmenter is integrated from [UETsegmenter](https://github.com/phongnt570/UETsegmenter).

## How to use

The following command is used to run this toolkit, your PC needs JDK 1.8 or newer:

```
java -jar uetnlp.jar [options...] [arguments..]
	-res FILE : the resources folder containing model for WS and configure file for POS tagging (require)
	-in FILE  : the input file/directory (require)
	-ext VAL  : specify file types to process (in the case -in is a directory) (default: .txt)
	-tok      : specify if doing tokenization is set or not (default: false)
	-sent     : specify if doing sentence segmentation is set or not (default: false)
	-ws       : specify if doing word segmentation is set or not (default: false)
	-pos      : specify if doing pos tagging or not is set or not (default: false)
```

## Author

Phong Tuan Nguyen

University of Engineering and Technology, Vietnam National University - Hanoi

Email: phongnt.uet@gmail.com