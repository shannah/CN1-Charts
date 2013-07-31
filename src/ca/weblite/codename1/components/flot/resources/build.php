<?php
$lines = file('flot.html');
foreach ( $lines as $line){
    echo 'sb.append("'.str_replace(array('\\',"\n", '"'), array('\\\\', "\\n",'\\"'), $line).'");'."\n";
}
