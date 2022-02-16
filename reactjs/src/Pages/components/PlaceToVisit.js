import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ImageCard from './ImageCard';
const useStyles = makeStyles((theme) => ({
  root: {
    minHeight: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    [theme.breakpoints.down('md')]: {
      flexDirection: 'column',
    },
  },
}));
export default function () {
  const classes = useStyles();
  const checked = true;
  return (
    <div className={classes.root} id="place-to-visit">
      <ImageCard place="image1.png" checked={checked} />
      <ImageCard place="image2.png" checked={checked} />
    </div>
  );
}
