% 
% arff_saver.m
%
% This script saves feature values to Amuse ARFF feature file
% Created on 22.03.2007 by Igor Vatolkin
% Last modified on 10.07.2009 by Igor Vatolkin
% $Id: $
function arff_saver(file_name,feature_name,data,window_size_in_samples)

% Create Amuse ARFF feature file
file = fopen(file_name,'w');

% Write header
fprintf(file,'@RELATION ''Music feature''\n\n');
fprintf(file,['%%rows=',num2str(size(data,1)),'\n']);
fprintf(file,['%%columns=',num2str(size(data,2)),'\n']);
fprintf(file,'%%sample_rate=22050\n');
if exist('window_size_in_samples')
	fprintf(file,['%%window_size=',num2str(window_size_in_samples),'\n\n']);
else
	fprintf(file,'%%window_size=512\n\n');
end
fprintf(file,['@ATTRIBUTE ''',feature_name,''' NUMERIC\n']);

% If the feature is multidimensional...
for j = 2:size(data,1)
   	fprintf(file,['@ATTRIBUTE ''',feature_name,''' NUMERIC\n']);
end
fprintf(file,'\n@DATA\n');

% Go through all time windows
for i = 1:size(data,2)
    
    % Write the value of the first feature dimension
    fprintf(file,num2str(data(1,i),12));
    
    % Go through the remaining feature values if it has more than one
    % dimension and write them
    for j = 2:size(data,1)
    	fprintf(file,[',',num2str(data(j,i),12)]);
    end
    fprintf(file,'\n');
end

% Close the file
fclose(file);

